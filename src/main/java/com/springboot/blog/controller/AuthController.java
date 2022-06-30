package com.springboot.blog.controller;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.SignUpDto;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.security.JWTAuthResponse;
import com.springboot.blog.security.JwtTokenProvider;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authloginUser(@RequestBody LoginDto loginDto){

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtTokenProvider.generateToken(authenticate);
        return  ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> authSignup(@RequestBody SignUpDto signUpDto) {

        //check for username
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is Laready taken !!!!", HttpStatus.BAD_REQUEST);
        }

        //check for email
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email Name is already taken !!!!",HttpStatus.OK);
        }

        //Create user Ob Object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        User save = userRepository.save(user);

        return new ResponseEntity<>("User Added Successfully !!!!",HttpStatus.CREATED);
    }
}
