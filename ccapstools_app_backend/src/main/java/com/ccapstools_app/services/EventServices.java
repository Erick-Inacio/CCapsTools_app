package com.ccapstools_app.services;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccapstools_app.data.dto.EventDTO;
import com.ccapstools_app.data.vo.EventVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.event.ActivityModel;
import com.ccapstools_app.models.event.EventModel;
import com.ccapstools_app.repositories.EventRepository;

public class EventServices {

    private static final Logger logger = Logger.getLogger(ActivityServices.class.getName());

    @Autowired
    EventRepository eventRepository;

    public List<EventDTO> findAll() {
        logger.info("find all Activity");

        List<EventModel> events = eventRepository.findAll();
        if (events == null || events.isEmpty()) {
            logger.warning("No events found");
            return Collections.emptyList();
        }

        try {
            return DozerMapper.parseListObjects(events, EventDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing events to DTOs", e);
            return Collections.emptyList();
        }

    }

    public EventDTO findById(Long id) {
        logger.info("find Activity by id");

        EventModel events = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(events, EventDTO.class);
    }

    public EventDTO create(EventVO eventVo) {
        logger.info("create Activity");

        if (eventVo == null) {
            logger.warning("Event is null");
            throw new IllegalArgumentException("Event is null");
        }

        EventModel event = DozerMapper.parseObject(eventVo, EventModel.class);

        return DozerMapper.parseObject(eventRepository.save(event), EventDTO.class);
    }

    public EventDTO update(EventVO updatedEventVo) throws Exception {
        logger.info("update Activity");

        if (updatedEventVo == null || updatedEventVo.getId() == null) {
            throw new IllegalArgumentException("EventVO inválido para atualização.");
        }

        EventModel existingEvent = eventRepository.findById(updatedEventVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id: " + updatedEventVo.getId()));

        if (updatedEventVo.getInitialDateTime() != null) {
            existingEvent.setInitialDateTime(updatedEventVo.getInitialDateTime());
        }
        if (updatedEventVo.getFinalDateTime() != null) {
            existingEvent.setFinalDateTime(updatedEventVo.getFinalDateTime());
        }
        if (updatedEventVo.getActivities() != null) {
            existingEvent
                    .setActivities(DozerMapper.parseListObjects(updatedEventVo.getActivities(), ActivityModel.class));
        }
        if (updatedEventVo.getDescription() != null) {
            existingEvent.setDescription(updatedEventVo.getDescription());
        }

        try {
            EventModel updatedEventModel = eventRepository.save(existingEvent);
            return DozerMapper.parseObject(updatedEventModel, EventDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao salvar a Atividade atualizada");
            throw new Exception("Erro ao atualizar Activity no banco de dados");
        }
    }

    public void delete(Long id) {
        logger.info("Deletando event");

        var entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado para deletar"));

        try {
            eventRepository.delete(entity);
        } catch (Exception e) {
            throw e;
        }

    }

}
