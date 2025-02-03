package com.ccapstools_app.controllers;

import java.util.List;

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

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll() {
        return userServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findById(@PathVariable(value = "id") Long id) {
        return userServices.findById(id);
    }

    @GetMapping(value = "/firebase/{uid}")
    public UserDTO getUserByuid(@PathVariable String uid) {
        Long userId = userServices.getUserIdByUid(uid);
        return userServices.findById(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@RequestBody UserVO UserVO) {

        try {
            return ResponseEntity.ok(userServices.create(UserVO));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO update(@RequestBody UserVO UserVO) {

        return userServices.update(UserVO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        userServices.delete(id);

        return ResponseEntity.noContent().build();
    }

}
