package com.example.board.service;

import com.example.board.domain.BoardDto;
import com.example.board.mapper.BoardMapper;
import com.example.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDto> selectListPaging(Criteria criteria){
        return boardMapper.selectListPaging(criteria);
    }

    public BoardDto selectDetail(int boardSeq){
        return boardMapper.selectDetail(boardSeq);
    }

    public int totalCount() {
        return boardMapper.totalCount();
    }

    public int newInsertBoard(BoardDto boardDto){
        return  boardMapper.newInsertBoard(boardDto);
    }

    public int changeLocation(HashMap hashMap){
        return boardMapper.changeLocation(hashMap);
    }

    public int insertReply(BoardDto boardDto){
        return boardMapper.insertReply(boardDto);
    }

    public int updateBoard(BoardDto boardDto){
        return boardMapper.updateBoard(boardDto);
    }

    public int deleteBoard(HashMap hashMap){
        return boardMapper.deleteBoard(hashMap);
    };
}
