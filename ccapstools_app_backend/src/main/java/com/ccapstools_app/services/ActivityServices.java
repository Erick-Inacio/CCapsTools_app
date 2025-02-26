package com.ccapstools_app.services;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccapstools_app.data.dto.ActivityDTO;
import com.ccapstools_app.data.vo.ActivityVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.models.event.ActivityModel;
import com.ccapstools_app.repositories.ActivityRepository;
import com.ccapstools_app.mapper.DozerMapper;

public class ActivityServices {

    private static final Logger logger = Logger.getLogger(ActivityServices.class.getName());

    @Autowired
    ActivityRepository activityRepository;

    public List<ActivityDTO> findAll() {
        logger.info("find all Activity");

        List<ActivityModel> activities = activityRepository.findAll();
        if (activities == null || activities.isEmpty()) {
            logger.warning("No activities found");
            return Collections.emptyList();
        }

        try{
            return DozerMapper.parseListObjects(activities, ActivityDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing activities to DTOs", e);
            return Collections.emptyList();
        }

    }

    public ActivityDTO findById(Long id) {
        logger.info("find Activity by id");
        
        ActivityModel activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));
        
        return DozerMapper.parseObject(activity, ActivityDTO.class);
    }

    public ActivityDTO create(ActivityVO activityVo) {
        logger.info("create Activity");

        if(activityVo == null) {
            logger.warning("Activity is null");
            throw new IllegalArgumentException("Activity is null");
        }
        
        if(activityVo.getEvent() == null) {
            throw new IllegalArgumentException("Event is null");
        }
        
        
    }
    
}
