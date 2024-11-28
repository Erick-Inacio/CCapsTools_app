package com.ccapstools_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.UsersDTO;
import com.ccapstools_app.data.vo.UsersVO;
import com.ccapstools_app.services.UsersServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    
    @Autowired
    private UsersServices usersServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDTO> findAll() {
        return usersServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDTO getMethodName(@PathVariable(value = "id") long id) {
        return usersServices.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> create(@RequestBody UsersVO usersVO) {
        
        try{
            return ResponseEntity.ok(usersServices.create(usersVO));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UsersDTO update(@RequestBody UsersVO usersVO) {
        
        return usersServices.update(usersVO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        usersServices.delete(id);

        return ResponseEntity.noContent().build();
    }
 
}
