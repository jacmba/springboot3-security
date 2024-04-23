package net.jazbelt.springboot3security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {

    @PostMapping("/authenticate")
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }
}
