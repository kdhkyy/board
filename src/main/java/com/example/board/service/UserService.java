package com.example.board.service;

import com.example.board.domain.UserDto;
import com.example.board.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDto findById(UserDto userDto) {
        if(userDto == null) throw new IllegalStateException("올바르지 않은 요청");

        if(StringUtils.isBlank(userDto.getUserId())) throw new IllegalStateException("아이디를 입력해주세요.");

        if(StringUtils.isBlank(userDto.getPasswd())) throw new IllegalStateException("비밀번호를 입력해주세요.");

        if(userDto.getUserId().length() > 10) throw new IllegalStateException("아이디가 10자리를 넘었습니다.");

        if(userDto.getPasswd().length() > 16) throw new IllegalStateException("비밀번호가 16자리를 넘었습니다.");

        return userMapper.findById(userDto);
    }

};
