package com.jwtproject.auth.controller;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.auth.model.JwtRequest;
import com.jwtproject.auth.model.JwtResponse;
import com.jwtproject.auth.service.VendorServiceImpl;
import com.jwtproject.config.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private AuthenticationProvider authenticationManager;

    private JwtTokenUtils jwtTokenUtil;

    private VendorServiceImpl vendorService;
    private VendorRepository vendorRepository;

    @Autowired
    public JwtAuthenticationController(AuthenticationProvider authenticationManager, JwtTokenUtils jwtTokenUtil, VendorServiceImpl vendorService, VendorRepository vendorRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.vendorService = vendorService;
        this.vendorRepository = vendorRepository;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println(authenticationRequest.getVendorEmail());
        System.out.println(authenticationRequest.getPassword());
        authenticate(authenticationRequest.getVendorEmail(), authenticationRequest.getPassword());

        System.out.println(authenticationRequest.getVendorEmail());
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(vendorRepository.findByVendorEmail(authenticationRequest.getVendorEmail()));

        System.out.println(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}