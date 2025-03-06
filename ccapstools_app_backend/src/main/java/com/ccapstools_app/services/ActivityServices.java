package com.ccapstools_app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ccapstools_app.data.dto.ActivityDTO;
import com.ccapstools_app.data.vo.ActivityVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.event.ActivityModel;
import com.ccapstools_app.models.event.EventModel;
import com.ccapstools_app.models.users.SpeakerModel;
import com.ccapstools_app.repositories.ActivityRepository;
import com.ccapstools_app.repositories.EventRepository;
import com.ccapstools_app.repositories.SpeakerRepository;
import com.google.firebase.database.DatabaseException;

@Service
public class ActivityServices {

    private static final Logger logger = Logger.getLogger(ActivityServices.class.getName());

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    @Lazy
    EventRepository eventRepository;

    @Autowired
    @Lazy
    SpeakerRepository speakerRepository;

    // Bascic CRUD Methods
    // Select All
    public List<ActivityDTO> findAll() {
        logger.info("find all Activity");

        List<ActivityModel> activities = activityRepository.findAll();
        if (activities == null || activities.isEmpty()) {
            logger.warning("No activities found");
            return Collections.emptyList();
        }

        try {
            return DozerMapper.parseListObjects(activities, ActivityDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing activities to DTOs", e);
            return Collections.emptyList();
        }

    }

    // Select by id
    public ActivityDTO findById(Long id) {
        logger.info("find Activity by id");

        ActivityModel activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(activity, ActivityDTO.class);
    }

    // Insert
    public ActivityDTO create(ActivityVO activityVo) {
        logger.info("create Activity");

        if (activityVo == null) {
            logger.warning("Activity is null");
            throw new IllegalArgumentException("Activity is null");
        }

        if (activityVo.getEvent() == null) {
            throw new IllegalArgumentException("Event is null");
        }

        try {
            EventModel event = eventRepository.findById(activityVo.getEvent().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No records found for this id: " + activityVo.getEvent()));

            List<SpeakerModel> speakers = new ArrayList<>();

            if (activityVo.getSpeakers() != null) {
                for (Long speakerId : activityVo.getSpeakers()) {
                    SpeakerModel speaker = speakerRepository.findById(speakerId)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "No records found for this id: " + speakerId));
                    speakers.add(speaker);
                }
            }

            ActivityModel activity = DozerMapper.parseObject(activityVo, ActivityModel.class);
            event.addActivity(activity);
            activity.setSpeakers(speakers);

            ActivityModel savedActivity = activityRepository.save(activity);

            return DozerMapper.parseObject(savedActivity, ActivityDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating activity", e);
            throw e;
        }
    }

    // Update
    public ActivityDTO update(ActivityVO updatedActivityVo) {
        logger.info("update Activity");

        if (updatedActivityVo == null || updatedActivityVo.getId() == null) {
            throw new IllegalArgumentException("ActivityVO inválido para atualização.");
        }

        ActivityModel existingActivity = activityRepository.findById(updatedActivityVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id: " + updatedActivityVo.getId()));

        if (updatedActivityVo.getActivityName() != null) {
            existingActivity.setActivityName(updatedActivityVo.getActivityName());
        }
        if (updatedActivityVo.getActivityType() != null) {
            existingActivity.setActivityType(updatedActivityVo.getActivityType());
        }
        if (updatedActivityVo.getAimedAudience() != null) {
            existingActivity.setAimedAudience(updatedActivityVo.getAimedAudience());
        }
        if (updatedActivityVo.getDates() != null) {
            existingActivity.setDates(updatedActivityVo.getDates());
        }
        if (updatedActivityVo.getDescription() != null) {
            existingActivity.setDescription(updatedActivityVo.getDescription());
        }
        // if (updatedActivityVo.getSpeakers() != null) {
        // existingActivity
        // .setSpeakers(DozerMapper.parseListObjects(updatedActivityVo.getSpeakers(),
        // SpeakerModel.class));
        // }
        if (updatedActivityVo.getDuration() != null) {
            existingActivity.setDuration(updatedActivityVo.getDuration());
        }
        if (updatedActivityVo.getLocal() != null) {
            existingActivity.setLocal(updatedActivityVo.getLocal());
        }
        if (updatedActivityVo.getHardSoftwareRequired() != null) {
            existingActivity.setHardSoftwareRequired(updatedActivityVo.getHardSoftwareRequired());
        }
        if (updatedActivityVo.getPrerequisite() != null) {
            existingActivity.setPrerequisite(updatedActivityVo.getPrerequisite());
        }

        existingActivity.setApproved(updatedActivityVo.isApproved());

        try {
            ActivityModel updatedActivityModel = activityRepository.save(existingActivity);
            return DozerMapper.parseObject(updatedActivityModel, ActivityDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao salvar a Atividade atualizada");
            throw new DatabaseException("Erro ao atualizar Activity no banco de dados");
            // FIXME: n faz sentido esse import do firebase aqui
        }
    }

    // Delete
    public void delete(Long id) {
        logger.info("Deletando atividade");

        var entity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado para deletar"));

        try {
            activityRepository.delete(entity);
        } catch (Exception e) {
            throw e;
        }

    }

    // Personilised consults Methods
    public List<ActivityDTO> findAllByEventId(Long eventId) {
        if (eventId == null) {
            throw new IllegalArgumentException("EventId is null");
        }
        try {
            return DozerMapper.parseListObjects(activityRepository.findAllByEventId(eventId), ActivityDTO.class);
        } catch (Exception e) {
            throw new DatabaseException("An error occurred while retrieving the activity", e);
        }
    }

}
