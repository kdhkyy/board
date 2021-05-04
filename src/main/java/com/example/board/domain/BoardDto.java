package com.example.board.domain;

import lombok.Data;

@Data
public class BoardDto {
    //primitive type : long
    private String boardSeq;

    //primitive type : long
    private String parentSeq;

    //primitive type : long
    private String groupSeq;

    //primitive type : long
    private String boardSort;

    //primitive type : long
    private String boardDepth;

    private String userId;
    private String title;
    private String content;
    private String registDate;
    private String registId;
    private String modifyDate;
    private String modifyId;
}
