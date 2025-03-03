package com.ccapstools_app.controllers.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.ActivityDTO;
import com.ccapstools_app.data.dto.EventDTO;
import com.ccapstools_app.data.vo.EventVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.services.EventServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventServices eventServices;

    // Endpoints verbos HTTPs
    // Gets
    @Operation(summary = "Lista todos os eventos", description = "Retorna uma lista de Eventos cadastrados no sistema")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDTO>> getAll() {
        try {
            List<EventDTO> events = eventServices.findAll();
            if (events == null || events.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'SPEAKER', 'COMMISSION')")
    @Operation(summary = "Busca um evento pelo id", description = "Retorna um Evento cadastrado no sistema")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            EventDTO event = eventServices.findById(id);
            return ResponseEntity.ok(event);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Post
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Cria um novo evento", description = "Cria um novo Evento no sistema")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> create(@RequestBody EventVO eventVO) {
        if (eventVO == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(eventServices.create(eventVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    // Put
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Altera um evento", description = "Altera um Evento no sistema")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> update(@RequestBody EventVO eventVO) {

        if (eventVO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok(eventServices.update(eventVO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Deleta um evento", description = "Deleta um Evento no sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            eventServices.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoinst Personalizados
    // get
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_SPEAKER', 'ROLE_COMMISSION')")
    @GetMapping(value = "/getActivitisByEventId/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivityDTO>> getActivitiesByEventId(@PathVariable Long eventId) {
        if (eventId == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(eventServices.getActivitiesByEventId(eventId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        //TODO: verificar se as permissões estão sendo validadas
    }

}
