package com.example.board.service;

import com.example.board.domain.UserDto;
import com.example.board.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Optional<UserDto>  findByIdAndPasswd(UserDto userDto){
        return userMapper.findByIdAndPasswd(userDto);
    }

};
