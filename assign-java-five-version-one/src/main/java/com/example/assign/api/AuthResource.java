package com.example.assign.api;

import com.example.assign.dto.UserDTO;
import com.example.assign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthResource {

    private final UserService userService;

    @GetMapping("/get")
    public String get() {
        return "GET:: auth get";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.authenticate(dto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto) {
        if (userService.existsUserByUsername(dto.getUsername())) {
            dto.setMessage("Username is taken!..");
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.register(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.register(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "GET:: auth delete";
    }
}
