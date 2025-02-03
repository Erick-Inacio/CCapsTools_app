package com.ccapstools_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    public String verifyUser(HttpServletRequest request) {
        String userUid = (String) request.getAttribute("uid");
        return "Usuário autenticado com UID: " + userUid;
    }
}