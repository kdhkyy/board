package com.example.board.service;

import com.example.board.domain.BoardDto;
import com.example.board.mapper.BoardMapper;
import com.example.util.Criteria;
import com.example.util.TypeCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;
    TypeCheck typeCheck = new TypeCheck();

    @Autowired
    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDto> selectListPaging(Criteria criteria){
        return boardMapper.selectListPaging(criteria);
    }

    public BoardDto selectDetail(String boardSeq){
        if(!typeCheck.isNumeric(boardSeq)) throw new IllegalStateException("올바르지 않은 경로입니다.");
        return boardMapper.selectDetail(Long.parseLong(boardSeq));
    }

    public int totalCount(){
        return boardMapper.totalCount();
    }

    public int newInsertBoard(BoardDto boardDto){
        if(boardDto.getTitle().length() > 31) throw new IllegalStateException("제목의 길이가 비정상적으로 깁니다.");
        if(boardDto.getContent().length() > 100) throw new IllegalStateException("내용의 길이가 비정상적으로 깁니다.");
        return  boardMapper.newInsertBoard(boardDto);
    }

    public int changeLocation(HashMap hashMap){
        return boardMapper.changeLocation(hashMap);
    }

    public int insertReply(BoardDto boardDto){
        if(boardDto.getTitle().length() > 31) throw new IllegalStateException("제목의 길이가 비정상적으로 깁니다.");
        if(boardDto.getContent().length() > 100) throw new IllegalStateException("내용의 길이가 비정상적으로 깁니다.");
        return boardMapper.insertReply(boardDto);
    }

    public int updateBoard(BoardDto boardDto){
        if(!typeCheck.isNumeric(boardDto.getBoardSeq())) throw new IllegalStateException("비정상적인 접근입니다.");
        if(boardDto.getTitle().length() > 31) throw new IllegalStateException("제목의 길이가 비정상적으로 깁니다.");
        if(boardDto.getContent().length() > 100) throw new IllegalStateException("내용의 길이가 비정상적으로 깁니다.");
        return boardMapper.updateBoard(boardDto);
    }

    public long countReply(String boardSeq){
        if(!typeCheck.isNumeric(boardSeq)) throw new IllegalStateException("올바르지 않은 경로입니다.");
        return boardMapper.countReply(Long.parseLong(boardSeq));
    }

    public int deleteBoard(HashMap hashMap){
        if(!typeCheck.isNumeric(hashMap.get("boardSeq").toString())) throw new IllegalStateException("비정상적인 접근입니다.");
        return boardMapper.deleteBoard(hashMap);
    }
}
