package com.ccapstools_app.services;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccapstools_app.data.dto.SpeakerDTO;
import com.ccapstools_app.data.dto.UserDTO;
import com.ccapstools_app.data.vo.SpeakerVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.users.SpeakerModel;
import com.ccapstools_app.models.users.UserModel;
import com.ccapstools_app.repositories.SpeakerRepository;
import com.google.firebase.database.DatabaseException;

import jakarta.persistence.NoResultException;

@Service
public class SpeakerServices {
    private static final Logger logger = Logger.getLogger(SpeakerServices.class.getName());

    @Autowired
    SpeakerRepository speakerRepository;

    @Autowired
    UserServices userService;

    /* Basic CRUD Methods as http verbs */
    // gets
    // get all
    public List<SpeakerDTO> getAll() {
        logger.info("find all Speaker");

        List<SpeakerModel> speakers = speakerRepository.findAll();
        if (speakers == null || speakers.isEmpty()) {
            logger.warning("No speakers found");
            return Collections.emptyList();
        }

        try {
            return DozerMapper.parseListObjects(speakers, SpeakerDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing speakers to DTOs", e);
            return Collections.emptyList();
        }
    }

    // get by id
    public SpeakerDTO getById(Long id) {
        logger.info("find Speaker by id");

        SpeakerModel speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(speaker, SpeakerDTO.class);
    }

    // post
    @Transactional
    public SpeakerDTO post(SpeakerVO speakerVO) {
        logger.info("Criando Speaker...");

        if (speakerVO == null) {
            throw new IllegalArgumentException("SpeakerVO não pode ser nulo");
        }

        if (speakerVO.getUser() == null) {
            throw new IllegalArgumentException("ID do usuário no SpeakerVO não pode ser nulo");
        }
      
        try {
            // 🔥 Buscar o usuário no banco de dados e converter para entidade `User`
            UserDTO userDTO = userService.getById(speakerVO.getUser().getId());
            UserModel user = DozerMapper.parseObject(userDTO, UserModel.class);

            // 🔥 Criar Speaker manualmente (sem DozerMapper no user)
            SpeakerModel speaker = new SpeakerModel();
            speaker.setCompany(speakerVO.getCompany());
            speaker.setPosition(speakerVO.getPosition());
            speaker.setBio(speakerVO.getBio());
            speaker.setSocialMedia(speakerVO.getSocialMedia());

            // 🔥 Definir manualmente o usuário no speaker
            speaker.setUser(user);

            // 🔥 Salvar no banco de dados
            SpeakerModel savedSpeaker = speakerRepository.save(speaker);

            return DozerMapper.parseObject(savedSpeaker, SpeakerDTO.class);
        } catch (HibernateException e) {
            logger.log(Level.SEVERE, "Error creating Speaker", e);
            throw e;
        }
    }

    // put
    public SpeakerDTO put(SpeakerVO updatedSpeakerVo) {
        logger.info("Atualizando Speaker...");

        if (updatedSpeakerVo == null || updatedSpeakerVo.getId() == null) {
            throw new IllegalArgumentException("SpeakerVO inválido para atualização.");
        }

        SpeakerModel existingSpeaker = speakerRepository.findById(updatedSpeakerVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Speaker não encontrado para o ID: "
                        + updatedSpeakerVo.getId()));

        // Atualiza apenas se houver um novo User associado
        if (updatedSpeakerVo.getUser() != null) {
            try {
                UserDTO existingUserDTO = userService.getById(updatedSpeakerVo.getUser().getId());
                UserModel user = DozerMapper.parseObject(existingUserDTO, UserModel.class);

                existingSpeaker.setUser(user);
            } catch (ResourceNotFoundException e) {
                logger.log(Level.WARNING, "Usuário não encontrado para o ID: {0}, mantendo usuário atual.",
                        updatedSpeakerVo.getUser());
            }
            // FIXME:Garantir que essa verificação tem sentido
        }

        // Atualiza apenas os campos não nulos
        if (updatedSpeakerVo.getSocialMedia() != null) {
            existingSpeaker.setSocialMedia(updatedSpeakerVo.getSocialMedia());
        }
        if (updatedSpeakerVo.getCompany() != null) {
            existingSpeaker.setCompany(updatedSpeakerVo.getCompany());
        }
        if (updatedSpeakerVo.getPosition() != null) {
            existingSpeaker.setPosition(updatedSpeakerVo.getPosition());
        }
        if (updatedSpeakerVo.getBio() != null) {
            existingSpeaker.setBio(updatedSpeakerVo.getBio());
        }

        try {
            SpeakerModel updatedSpeaker = speakerRepository.save(existingSpeaker);
            return DozerMapper.parseObject(updatedSpeaker, SpeakerDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao salvar Speaker atualizado", e);
            throw new DatabaseException("Erro ao atualizar Speaker no banco de dados.");
        }
    }

    // delete
    public void delete(Long id) {
        logger.info("Deleting Speaker...");

        SpeakerModel speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Speaker not found for ID: " + id));

        speakerRepository.delete(speaker);
    }

    /* Personalized consults */
    // get
    public SpeakerDTO findSpeakerByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        try {
            SpeakerModel speaker = speakerRepository.findSpeakerByUserId(userId);

            return DozerMapper.parseObject(speaker, SpeakerDTO.class);
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("Speaker not found for user ID: " + userId);
        } catch (Exception e) {
            throw new DatabaseException("An error occurred while retrieving the speaker", e);
        }
    }

}