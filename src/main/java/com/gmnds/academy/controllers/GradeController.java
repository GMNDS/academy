package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AuthenticationDTO;
import com.gmnds.academy.dto.LoginDTO;
import com.gmnds.academy.dto.RegisterDTO;
import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.repositories.StudentRepository;
import jakarta.validation.Valid;
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
public class GradeController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StudentRepository repUser;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((StudentModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data) {

        if(this.repUser.findByEmail(data.login()) != null) return ResponseEntity.badRequest().build();

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
        StudentModel newuser = new StudentModel(data.ra(), data.login(), encodedPassword, data.role());

        this.repUser.save(newuser);

        return ResponseEntity.ok().build();

    }
}
