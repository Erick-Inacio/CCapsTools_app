package com.ccapstools_app.services;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccapstools_app.data.dto.UserDTO;
import com.ccapstools_app.data.vo.UserVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.users.UserModel;
import com.ccapstools_app.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@Service
public class UserServices {
    private static final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    /*Basic CRUD Methods as http verbs */
    
    // get All
    public List<UserDTO> getAll() {
        logger.info("find all User");

        return DozerMapper.parseListObjects(userRepository.findAll(), UserDTO.class);
    }

    // get by id
    public UserDTO getById(Long id) {
        logger.info("find user by id");

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(entity, UserDTO.class);
    }

    // post
    public UserDTO post(UserVO userVo) {
        logger.info("create user");

        if (userVo == null || userVo.getUid() == null || userVo.getRole() == null) {
            logger.warning("UserVO is null or has null fields, cannot create user");
            return null;
        }

        String uid = userVo.getUid();
        String role = userVo.getRole().name();

        try {
            FirebaseAuth.getInstance().setCustomUserClaims(uid, Map.of("role", role));
        } catch (FirebaseAuthException e) {
            logger.log(Level.SEVERE, "Error setting custom claims for user: " + uid, e);
            return null;
        }

        UserModel user = DozerMapper.parseObject(userVo, UserModel.class);

        try {
            return DozerMapper.parseObject(userRepository.save(user), UserDTO.class);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving user to database", e);
            return null;
        }
    }

    // put
    public UserDTO put(UserVO updatedUserVo) {
        logger.info("update user");

        UserModel existingUser = userRepository.findById(updatedUserVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id: " + updatedUserVo.getId() + "\n\n"));

        existingUser.setName(updatedUserVo.getName());
        existingUser.setEmail(updatedUserVo.getEmail());
        existingUser.setRole(updatedUserVo.getRole());
        existingUser.setRa(updatedUserVo.getRa());

        UserModel updatedUser = userRepository.save(existingUser);

        return DozerMapper.parseObject(updatedUser, UserDTO.class);
    }

    // delete
    public void delete(Long id) {
        logger.info("delete user");

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        userRepository.delete(entity);
    }

    /* Personalized methods */

    // get id by uid
    public Long getIdByUid(String uid) {
        return userRepository.getIdByUid(uid)
                .map(UserModel::getId) // Retorna o ID (Long)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
