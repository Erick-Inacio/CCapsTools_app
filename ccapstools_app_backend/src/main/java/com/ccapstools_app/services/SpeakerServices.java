package com.ccapstools_app.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccapstools_app.data.dto.SpeakerDTO;
import com.ccapstools_app.data.vo.SpeakerVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.Speaker;
import com.ccapstools_app.repositories.SpeakerRepository;

@Service
public class SpeakerServices {
    private static final Logger logger = Logger.getLogger(SpeakerServices.class.getName());

    @Autowired
    SpeakerRepository speakerRepository;

    public List<SpeakerDTO> findAll() {
        logger.info("find all Speaker");

        return DozerMapper.parseListObjects(speakerRepository.findAll(), SpeakerDTO.class);
    }

    public SpeakerDTO findById(Long id) {
        logger.info("find Speaker by id");

        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(speaker, SpeakerDTO.class);
    }

    public SpeakerDTO create(SpeakerVO speakerVO) {
        logger.info("create Speaker");

        Speaker speaker = DozerMapper.parseObject(speakerVO, Speaker.class);
        return DozerMapper.parseObject(speakerRepository.save(speaker), SpeakerDTO.class);
    }

    public SpeakerDTO update(SpeakerVO updatedSpeakerVo) {
        logger.info("update Speaker");

        Speaker existingSpeaker = speakerRepository.findById(updatedSpeakerVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: "
                        + updatedSpeakerVo.getId()));

        existingSpeaker.setSocialMedia(updatedSpeakerVo.getSocialMedia());
        existingSpeaker.setCompany(updatedSpeakerVo.getCompany());
        existingSpeaker.setPosition(updatedSpeakerVo.getPosition());
        existingSpeaker.setBio(updatedSpeakerVo.getBio());
    
        Speaker updatedSpeaker = speakerRepository.save(existingSpeaker);

        return DozerMapper.parseObject(updatedSpeaker, SpeakerDTO.class);
    }

    public void delete(Long id) {
        logger.info("delete Speaker");

        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        speakerRepository.delete(speaker);
    }
}