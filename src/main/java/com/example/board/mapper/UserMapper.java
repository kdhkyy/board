package com.example.board.mapper;

import com.example.board.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM TB_USER WHERE USER_ID = #{UserDto.userId}")
    UserDto findById(@Param("UserDto") UserDto userDto);

}
