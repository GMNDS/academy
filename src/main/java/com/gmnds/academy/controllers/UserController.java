package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddUserDTO;
import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.UserModel;
import com.gmnds.academy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repUser;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return repUser.findAll();
    }

    private boolean validateUserAcess(Long id, String authorization) {

        String token = authorization.replace("Bearer ", "");
        Long authenticatedUserId = tokenService.getUserId(token);

        return id.equals(authenticatedUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable  Long id, @RequestHeader("Authorization") String authorization) {
        if (!validateUserAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return repUser.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody AddUserDTO data, @RequestHeader("Authorization") String authorization) {
        if (!validateUserAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<UserModel> us = repUser.findById(id);

        if (us.isPresent()) {
            UserModel existingUser = us.get();
            existingUser.setName(data.name());
            existingUser.setEmail(data.email());
            existingUser.setPassword(data.password());

            UserModel updatedUser = repUser.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        }
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        Long authenticatedUserId = tokenService.getUserId(token);

        Optional<UserModel> us = repUser.findById(authenticatedUserId);

        if (us.isPresent()) {
            boolean active = us.get().isActive();
            if (active) {
                us.get().setActive(false);
                repUser.save(us.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

}
