package com.example.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private String userId;
    private String passwd;
    private String name;
    private char sex;
    private short age;
};
