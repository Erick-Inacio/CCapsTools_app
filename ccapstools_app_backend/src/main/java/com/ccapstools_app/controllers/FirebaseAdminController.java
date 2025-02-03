package com.ccapstools_app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.utils.enums.Roles;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/admin")
public class FirebaseAdminController {

    @PostMapping("/set-role/{uid}")
    public String setUserRole(@PathVariable String uid, @RequestParam Roles role) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", role.name()); // 🔥 Define a role no Firebase

            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
            return "✅ Role " + role.name() + " atribuída ao usuário " + uid;

        } catch (FirebaseAuthException e) {
            return "❌ Erro ao definir role: " + e.getMessage();
        }
    }

    @GetMapping("/get-claims/{uid}")
    public Map<String, Object> getUserClaims(@PathVariable String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid).getCustomClaims();
    }

}
