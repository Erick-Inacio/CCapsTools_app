package com.ccapstools_app.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccapstools_app.data.dto.UserDTO;
import com.ccapstools_app.data.vo.UserVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.users.UserModel;
import com.ccapstools_app.repositories.UserRepository;

@Service
public class UserServices {
    private static final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository userRepository;

    // Basic CRUD Methods
    // Select All
    public List<UserDTO> findAll() {
        logger.info("find all User");

        return DozerMapper.parseListObjects(userRepository.findAll(), UserDTO.class);
    }

    // Select by id
    public UserDTO findById(Long id) {
        logger.info("find user by id");

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(entity, UserDTO.class);
    }

    // Insert
    public UserDTO create(UserVO UserVo) {
        logger.info("create user");

        UserModel User = DozerMapper.parseObject(UserVo, UserModel.class);
        return DozerMapper.parseObject(userRepository.save(User), UserDTO.class);
    }

    public UserDTO update(UserVO updatedUserVo) {
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

    public void delete(Long id) {
        logger.info("delete user");

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        userRepository.delete(entity);
    }

    public Long getUserIdByUid(String uid) {
        return userRepository.findByUid(uid)
                .map(UserModel::getId) // Retorna o ID (Long)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
