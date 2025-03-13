package com.ccapstools_app.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.UserDTO;
import com.ccapstools_app.data.vo.UserVO;
import com.ccapstools_app.services.UserServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    // Basic Http methods
    // Get
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de Usuários cadastrados no sistema")
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> findAll() {
        try {
            List<UserDTO> users = userServices.getAll();
            if (users == null || users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get by id
    @Operation(summary = "Busca um usuário pelo id", description = "Retorna um Usuário cadastrado no sistema")
    @GetMapping(value = "/getById", params = { "id" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@RequestParam Long id) {
        try {
            UserDTO user = userServices.getById(id);
            if (user == null || user.getId() == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Post
    @Operation(summary = "Cria um novo usuário", description = "Cria um novo Usuário no sistema")
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> post(@RequestBody UserVO UserVO) {

        try {
            UserDTO user = userServices.post(UserVO);
            if (user == null || user.getId() == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Put
    @Operation(summary = "Atualiza um usuário", description = "Atualiza um Usuário no sistema")
    @PutMapping(value = "/put", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> put(@RequestBody UserVO UserVO) {
        try {
            UserDTO user = userServices.put(UserVO);
            if (user == null || user.getId() == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete
    @Operation(summary = "Deleta um usuário", description = "Deleta um Usuário no sistema")
    @DeleteMapping(value = "/delete", params = { "id" })
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().build();
            }
            userServices.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Persnalized enpoints

    // Get by uid
    @Operation(summary = "Busca um usuário pelo uid", description = "Retorna um Usuário cadastrado no sistema")
    @GetMapping(value = "/getByUid", params = { "uid" })
    public ResponseEntity<UserDTO> getByuid(@RequestParam String uid) {
        try {
            Long userId = userServices.getIdByUid(uid);
            UserDTO user = userServices.getById(userId);
            if (user == null || user.getId() == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
