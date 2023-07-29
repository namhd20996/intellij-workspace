package com.example.assign.api;

import com.example.assign.api.output.AuthenticationResp;
import com.example.assign.converter.UserConverter;
import com.example.assign.dto.UserDTO;
import com.example.assign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthResource {

    private final UserService userService;

    private final UserConverter userConverter;

    @GetMapping("/get")
    public String get() {
        return "GET:: auth get";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResp> authenticate(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userConverter.authenticationResp(userService.authenticate(dto)), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResp> register(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userConverter.authenticationResp(userService.register(dto)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.register(dto), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> enabledAuth(@RequestParam("token") String token) {
        return new ResponseEntity<>(userService.confirmToken(token), HttpStatus.OK);
    }

    @PutMapping("/update/role/{id}/{authorize}")
    public ResponseEntity<UserDTO> updateRole(@PathVariable("id") UUID uuid,
                                              @PathVariable("authorize") String authorize) {
        return new ResponseEntity<>(userService.updateUserByRole(uuid, authorize), HttpStatus.OK);
    }

    @DeleteMapping("/delete/role/{id}/{authorize}")
    public ResponseEntity<UserDTO> deleteRole(@PathVariable("id") UUID uuid,
                                              @PathVariable("authorize") String authorize) {
        return new ResponseEntity<>(userService.deleteUserByRole(uuid, authorize), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "GET:: auth delete";
    }

    @PostMapping("/logout")
    public String logout() {
        return "POST:: auth logout";
    }
}
