package com.example.board.controller;

import com.example.board.domain.BoardDto;
import com.example.board.domain.UserDto;
import com.example.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardRestContoller {

    private final BoardService boardService;
    String res;

    @Autowired
    public BoardRestContoller(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping(value = "/register/board")
    public String saveBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        try {
            UserDto userDto = (UserDto) request.getSession().getAttribute("user");
            boardDto.setUserId(userDto.getUserId());
            log.info("INPUT VALUE : " + boardDto.getTitle());
            boardService.newInsertBoard(boardDto);
            res = "저장 완료!!";
        } catch (Exception e) {
            res = "저장 ERROR!!";
            e.printStackTrace();
        }
        return res;
    }

    @ResponseBody
    @PostMapping(value = "/modify/board")
    public String updateBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        try {
            UserDto userDto = (UserDto) request.getSession().getAttribute("user");
            boardDto.setUserId(userDto.getUserId());
            log.info("INPUT VALUE : " + boardDto);
            boardService.updateBoard(boardDto);
            res = "수정 완료!!";
        } catch (Exception e) {
            res = "수정 ERROR!!";
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping(value = "/reply/board")
    public String replyBoard(@RequestBody BoardDto boardDto, HttpSession session){
        res = "답글 저장 완료!!";
        try{
            UserDto userDto = (UserDto) session.getAttribute("user");
            boardDto.setUserId(userDto.getUserId());
            log.info("INPUT VALUE : " + boardDto);
            HashMap hashMap = new HashMap();
            hashMap.put("groupSeq", boardDto.getGroupSeq());
            hashMap.put("boardSeq", boardDto.getBoardSeq());
            boardService.changeLocation(hashMap);
            boardService.insertReply(boardDto);
        }catch (Exception e){
            res = "답글 저장 ERROR";
            e.printStackTrace();
        }
        return res;
    }

    @PatchMapping("/boards/{idx}")
    public String deleteBoard(@PathVariable long idx, HttpSession session){
        res = "삭제 완료";
        HashMap hashMap = new HashMap();
        hashMap.put("boardSeq",idx);
        hashMap.put("userId", ((UserDto) session.getAttribute("user")).getUserId());
        try{
            boardService.deleteBoard(hashMap);
        }catch (Exception e){
            res = "삭제 ERROR!!";
            e.printStackTrace();
        }
        return res;
    }
};
