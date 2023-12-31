package com.exam.examinationsystem.controller;

import com.exam.examinationsystem.config.JwtUtil;
import com.exam.examinationsystem.helper.UserNotFoundException;
import com.exam.examinationsystem.models.JwtRequest;
import com.exam.examinationsystem.models.JwtResponse;
import com.exam.examinationsystem.models.User;
import com.exam.examinationsystem.service.implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }

        //authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }




    private void authenticate(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


        } catch (DisabledException e) {
            throw new Exception("USER DISABLED "+e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials "+e.getMessage());
        }
    }

    //returns the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }

}
