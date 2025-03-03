package com.ccapstools_app.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.SpeakerDTO;
import com.ccapstools_app.data.vo.SpeakerVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.services.SpeakerServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerServices speakerServices;

    //Basic HTTP verbs

    //Gets
    //Gets all
    @Operation(summary = "Lista todos os palestrantes", description = "Retorna uma lista de Palestrantes cadastrados no sistema")
    @GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpeakerDTO>> findAll() {
        try {
            List<SpeakerDTO> speakers = speakerServices.findAll();
            if (speakers == null || speakers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(speakers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //Get by id
    @Operation(summary = "Busca um palestrante pelo id", description = "Retorna um Palestrante cadastrado no sistema")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeakerDTO> findById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            SpeakerDTO speaker = speakerServices.findById(id);
            return ResponseEntity.ok(speaker);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //Post
    @Operation(summary = "Cria um novo palestrante", description = "Cria um novo Palestrante no sistema")
    @PostMapping(value = "/post",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeakerDTO> create(@RequestBody SpeakerVO speakerVO) {
        if (speakerVO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok(speakerServices.create(speakerVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //Put
    @Operation(summary = "Atualiza um palestrante", description = "Atualiza um Palestrante no sistema")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeakerDTO> update(@RequestBody SpeakerVO speakerVO) {
        if (speakerVO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok(speakerServices.update(speakerVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //Personalized HTTP verbs
    //get by userId
    @Operation(summary = "Busca um palestrante pelo id do usuário", description = "Retorna um Palestrante cadastrado no sistema")
    @GetMapping(value = "/findUser/{userId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeakerDTO> findSpeakerByUserId(@PathVariable Long userId) {

        if(userId == null) {
            return null;
        }
        
        try {
            return ResponseEntity.ok(speakerServices.findSpeakerByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
