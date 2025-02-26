package com.ccapstools_app.controllers.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccapstools_app.services.EventServices;

@RestController
@RequestMapping("/api/event")
public class EventController {
    

    @Autowired
    private EventServices eventServices;
}
