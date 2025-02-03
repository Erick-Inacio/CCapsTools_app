package com.ccapstools_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.data.dto.SpeakerDTO;
import com.ccapstools_app.services.SpeakerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/speaker")
public class SpeakerController {
    
    @Autowired
    private SpeakerServices speakerServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpeakerDTO> findAll() {
        return speakerServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SpeakerDTO findById(@PathVariable(value = "id") Long id) {
        return speakerServices.findById(id);
    }
    

}
