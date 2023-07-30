package com.example.assign.user;

import com.example.assign.confirmtoken.ConfirmationToken;
import com.example.assign.confirmtoken.ConfirmationTokenDTO;
import com.example.assign.confirmtoken.ConfirmationTokenService;
import com.example.assign.constant.SystemConstant;
import com.example.assign.emai.EmailService;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.exception.DuplicateResourceException;
import com.example.assign.jwt.JwtService;
import com.example.assign.role.Role;
import com.example.assign.role.RoleRepo;
import com.example.assign.token.Token;
import com.example.assign.token.TokenRepo;
import com.example.assign.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserDTOMapper converter;

    private final JwtService jwtService;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenRepo tokenRepo;

    private final EmailService emailService;

    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public void register(UserRegistrationRequest request) {
        boolean isValidUser = existsUserByUsername(request.getUsername());
        if (isValidUser) {
            throw new DuplicateResourceException("User is exists!..");
        }
        boolean isValidEmail = existsByEmail(request.getEmail());
        if (isValidEmail) {
            throw new DuplicateResourceException("email is exists!..");
        }
        // List<Role> roles = roleRepo.findRoleByCode("admin:create").stream().toList();
        List<Role> roles = roleRepo.findRolesByName("ADMIN")
                .orElseThrow(() -> new ApiRequestException("Role with name not found"));
        User user = User.builder()
                .username(request.getUsername())
                .status(1)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        User userSave = userRepo.save(user);
        var jwtToken = jwtService.generateToken(userSave);
        confirmationTokenService.saveConfirmationToken(
                new ConfirmationToken(
                        jwtToken,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        userSave
                )
        );
        String link = "http://localhost:8080/api/auth/confirm?token=" + jwtToken;
        emailService.send(userSave.getEmail(), buildEmail(userSave.getUsername(), link));
        saveUserToken(userSave, jwtToken);
    }

    @Override
    public void updateUser(UserUpdateRequest request) {
        User user = userRepo.findUserByUsernameAndStatus(request.getUsername(), SystemConstant.STATUS_AUTH)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!..."));
        boolean isValidEmail = existsByEmail(request.getEmail());

        if (isValidEmail) {
            throw new ApiRequestException("Email: " + request.getEmail() + " is already.");
        }

        if (!user.getEmail().equals(request.getEmail())) {
            user.setEmail(request.getEmail());
        }

        if (user.getFirstname() == null || !user.getFirstname().equals(request.getFirstname())) {
            user.setFirstname(request.getFirstname());
        }

        if (user.getLastname() == null || !user.getLastname().equals(request.getLastname())) {
            user.setLastname(request.getLastname());
        }

        if (user.getPhone() == null || !user.getPhone().equals(request.getPhone())) {
            user.setPhone(request.getPhone());
        }

        if (user.getDob() == null || !user.getDob().equals(request.getDob())) {
            user.setDob(request.getDob());
        }

        userRepo.save(user);
    }

    @Override
    public UserDTO authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepo.findUserByUsernameAndStatus(request.getUsername(), SystemConstant.STATUS_AUTH)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!..."));
        UserDTO userDTO = converter.toDTO(user);
        var jwtToken = jwtService.generateToken(user);
        userDTO.setToken(jwtToken);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return userDTO;
    }

    @Override
    public void addRoleUser(UUID uuid, String authorize) {
        User user = userRepo.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("User not found!.."));
        Role role = roleRepo.findRoleByCode(authorize).orElseThrow(() -> new ApiRequestException("Authorize not found!.."));
        boolean isValidRole = user.getRoles().stream()
                .anyMatch(ro -> ro.getCode().equals(role.getCode()));
        if (isValidRole) {
            throw new ApiRequestException("Authorize is exists!..");
        }
        user.getRoles().add(role);
        userRepo.save(user);
    }

    @Override
    public void removeRoleUserByCode(UUID uuid, String authorize) {
        User user = userRepo.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("User not found!.."));
        Role role = roleRepo.findRoleByCode(authorize).orElseThrow(() -> new ApiRequestException("Authorize not found!.."));
        List<Role> roles = new ArrayList<>();
        user.getRoles().stream()
                .filter(ro -> !ro.getCode().equals(role.getCode()))
                .forEach(roles::add);
        user.setRoles(roles);
        userRepo.save(user);
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationTokenDTO confirmationToken = confirmationTokenService.findByToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new ApiRequestException("User already confirm!..");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }
        confirmationTokenService.setConfirmeAt(token);
        userRepo.updateUserByEmail(confirmationToken.getUser().getEmail());
        return "Confirm success!..";
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
