package com.ccapstools_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@RestController
@RequestMapping("/api/test")
public class FirebaseTestController {

    @GetMapping("/validate-token")
    public String validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            return "Token válido! UID: " + decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            return "Erro ao validar token: " + e.getMessage();
        }
    }
}
