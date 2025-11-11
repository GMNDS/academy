package com.gmnds.academy.services;

import com.gmnds.academy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    StudentRepository repUser;

    @Override
    @Cacheable(value = "userByEmail", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repUser.findByEmail(username);
    }
}
