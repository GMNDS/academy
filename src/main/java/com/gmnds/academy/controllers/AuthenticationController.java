package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.LoginDTO;
import com.gmnds.academy.dto.RegisterDTO;
import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.UserModel;
import com.gmnds.academy.repositories.UserRepository;
import jakarta.validation.Valid;
import com.gmnds.academy.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("auth")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repUser;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data) {

        if(this.repUser.findByEmail(data.login()) != null) return ResponseEntity.badRequest().build();

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newuser = new UserModel(data.login(), encodedPassword, data.role());

        this.repUser.save(newuser);

        return ResponseEntity.ok().build();

    }
}
