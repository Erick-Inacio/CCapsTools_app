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

import jakarta.annotation.Resource;

@Service
public class UsersServices {
    private Logger logger = Logger.getLogger(UsersServices.class.getName());

    @Autowired
    UsersRepository usersRepository;

    public List<UsersDTO> findAll(){
        logger.info("find all users");

        return DozerMapper.parseListObjects(usersRepository.findAll(), UsersDTO.class);
    }

    public UsersDTO findById(Long id){
        logger.info("find user by id");
        
        var entity = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id: " + id));
        
        return DozerMapper.parseObject(entity, UsersDTO.class);        
    }

    public UsersDTO create(UsersVO usersVo){
        logger.info("create user");

        Users users = DozerMapper.parseObject(usersVo, Users.class);
        return DozerMapper.parseObject(usersRepository.save(users), UsersDTO.class);
    }

    



}
