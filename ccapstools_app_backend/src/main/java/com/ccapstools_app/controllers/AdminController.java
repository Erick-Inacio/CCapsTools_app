package com.ccapstools_app.controllers;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.utils.enums.Role;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    Logger logger = Logger.getLogger(AdminController.class.getName());

    @PutMapping("/set-Role/{uid}")
    public String setUserRole(@PathVariable String uid, @RequestParam Role role) {
        try {
            FirebaseAuth.getInstance().setCustomUserClaims(uid, Map.of("role", role.name()));
            return "✅ Role " + role.name() + " atribuída ao usuário " + uid;

        } catch (FirebaseAuthException e) {
            return "❌ Erro ao definir o Role: " + e.getMessage();
        }
    }

    @GetMapping("/get-claims/{uid}")
    public Map<String, Object> getUserClaims(@PathVariable String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid).getCustomClaims();
    }

}
