package com.ccapstools_app.controllers.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.ActivityDTO;
import com.ccapstools_app.data.vo.ActivityVO;
import com.ccapstools_app.services.ActivityServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityServices activityServices;

    // Endpoinst verbos HTTP

    // Gets
    // Get all
    @Operation(summary = "Lista todas as atividades", description = "Retorna uma lista de atividades cadastradas no sistema")
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivityDTO>> getAll() {
        try {
            List<ActivityDTO> activities = activityServices.getAll();
            if (activities == null || activities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(activities);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get by id
    @Operation(summary = "Busca uma atividade pelo id", description = "Retorna uma atividade cadastrada no sistema")
    @GetMapping(value = "/getById", params = { "id" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivityDTO> getById(@RequestParam Long id) {
        try {
            ActivityDTO activity = activityServices.getById(id);
            if (activity == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Post
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SPEAKER')")
    @Operation(summary = "Cria uma nova atividade", description = "Cria uma nova atividade no sistema")
    @PostMapping(value = "/post",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivityDTO> post(@RequestBody ActivityVO activityVO) {

        if (activityVO == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(activityServices.post(activityVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    // Put
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Altera uma atividade", description = "Altera uma atividade cadastrada no sistema")
    @PutMapping(value = "/put",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivityDTO> put(@RequestBody ActivityVO activityVO) {
        if (activityVO == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(activityServices.put(activityVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Deleta uma atividade", description = "Deleta uma atividade cadastrada no sistema")
    @DeleteMapping(value = "/delete", params = { "id" })
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            activityServices.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
