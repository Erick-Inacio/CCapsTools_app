package com.ccapstools_app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.UserDTO;
import com.ccapstools_app.data.vo.UserVO;
import com.ccapstools_app.services.UserServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de Usuários cadastrados no sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll() {
        return userServices.findAll();
    }

    @Operation(summary = "Busca um usuário pelo id", description = "Retorna um Usuário cadastrado no sistema")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findById(@PathVariable Long id) {
        return userServices.findById(id);
    }

    @Operation(summary = "Busca um usuário pelo uid", description = "Retorna um Usuário cadastrado no sistema")
    @GetMapping(value = "/firebase/{uid}")
    public UserDTO getUserByuid(@PathVariable String uid) {
        Long userId = userServices.getUserIdByUid(uid);
        return userServices.findById(userId);
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo Usuário no sistema")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@RequestBody UserVO UserVO) {

        try {
            String uid = UserVO.getUid();
            String role = UserVO.getRole().name();

            if (uid == null || role == null) {
                return ResponseEntity.status(400).body(null);
            }

            FirebaseAuth.getInstance().setCustomUserClaims(uid, Map.of("role", role));

            return ResponseEntity.ok(userServices.create(UserVO));
        }catch (FirebaseAuthException ex) {
            return ResponseEntity.status(400).body(null);
        }catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um Usuário no sistema")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO update(@RequestBody UserVO UserVO) {

        return userServices.update(UserVO);
    }

    @Operation(summary = "Deleta um usuário", description = "Deleta um Usuário no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        userServices.delete(id);

        return ResponseEntity.noContent().build();
    }

}
