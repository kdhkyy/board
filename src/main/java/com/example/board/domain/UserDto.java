package com.example.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private String userId;
    private String passwd;
    private String name;

    //primitive type : char
    private String sex;

    //primitive type : short
    private String age;
};
