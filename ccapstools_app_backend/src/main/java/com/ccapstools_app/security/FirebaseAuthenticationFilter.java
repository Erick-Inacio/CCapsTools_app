package com.ccapstools_app.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println(" [FirebaseAuthFilter] Nenhum token encontrado. Seguindo como usuário anônimo.");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");
        System.out.println(" [FirebaseAuthFilter] Token recebido: " + token);

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String userId = decodedToken.getUid();
            String role = decodedToken.getClaims().get("role") != null ? decodedToken.getClaims().get("role").toString()
                    : "STUDENT"; // Captura a role

            System.out.println("✅ [FirebaseAuthFilter] Usuário autenticado:     " + userId + " | role: " + role);

            // 🔥 Adiciona a role ao Spring Security
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(" [FirebaseAuthFilter] Contexto de segurança atualizado!");

        } catch (FirebaseAuthException e) {
            System.out.println(" [FirebaseAuthFilter] Erro ao validar token: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido ou expirado.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
