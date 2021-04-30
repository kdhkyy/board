package com.example.board.domain;

import lombok.Data;

@Data
public class BoardDto {
    private long boardSeq;
    private long parentSeq;
    private long groupSeq;
    private long boardSort;
    private long boardDepth;
    private String userId;
    private String title;
    private String content;
    private String registDate;
    private String registId;
    private String modifyDate;
    private String modifyId;
    private char boardDelFlg;
}
