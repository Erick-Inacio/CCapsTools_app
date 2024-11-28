package com.ccapstools_app.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccapstools_app.data.dto.UsersDTO;
import com.ccapstools_app.data.vo.UsersVO;
import com.ccapstools_app.exceptions.ResourceNotFoundException;
import com.ccapstools_app.mapper.DozerMapper;
import com.ccapstools_app.models.Users;
import com.ccapstools_app.repositories.UsersRepository;

@Service
public class UsersServices {
    private Logger logger = Logger.getLogger(UsersServices.class.getName());

    @Autowired
    UsersRepository usersRepository;

    public List<UsersDTO> findAll() {
        logger.info("find all users");

        return DozerMapper.parseListObjects(usersRepository.findAll(), UsersDTO.class);
    }

    public UsersDTO findById(Long id) {
        logger.info("find user by id");

        var entity = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        return DozerMapper.parseObject(entity, UsersDTO.class);
    }

    public UsersDTO create(UsersVO usersVo) {
        logger.info("create user");

        Users users = DozerMapper.parseObject(usersVo, Users.class);
        return DozerMapper.parseObject(usersRepository.save(users), UsersDTO.class);
    }

    public UsersDTO update(UsersVO updatedUsersVo) {
        logger.info("update user");

        Users existingUser = usersRepository.findById(updatedUsersVo.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id: " + updatedUsersVo.getId() + "\n\n"));

        existingUser.setName(updatedUsersVo.getName());
        existingUser.setEmail(updatedUsersVo.getEmail());
        existingUser.setUserType(updatedUsersVo.getUserType());
        existingUser.setRa(updatedUsersVo.getRa());

        Users updatedUsers = usersRepository.save(existingUser);

        return DozerMapper.parseObject(updatedUsers, UsersDTO.class);
    }

    public void delete(Long id) {
        logger.info("delete user");

        var entity = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));

        usersRepository.delete(entity);
    }

}
