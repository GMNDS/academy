package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddInstitutionDTO;
import com.gmnds.academy.dto.UpdateInstitutionDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private AuthenticationManager authenticationManager;


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
