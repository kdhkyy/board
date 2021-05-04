package com.example.board.controller;

import com.example.board.domain.BoardDto;
import com.example.board.domain.UserDto;
import com.example.board.service.BoardService;
import com.example.util.TypeCheck;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.codehaus.commons.compiler.util.StringUtil;
import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardRestContoller {

    private final BoardService boardService;
    String res;
    TypeCheck typeCheck = new TypeCheck();

    @Autowired
    public BoardRestContoller(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @PostMapping(value = "/register/board")
    public String saveBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        System.out.println(boardDto);
        if(!StringUtils.isBlank(boardDto.getTitle())){
            try {
                UserDto userDto = (UserDto) request.getSession().getAttribute("user");
                boardDto.setRegistDate(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
                boardDto.setModifyId(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
                boardDto.setUserId(userDto.getUserId());
                log.info("INPUT VALUE : " + boardDto.getTitle());
                boardService.newInsertBoard(boardDto);
                res = "저장 완료!!";
            } catch (Exception e) {
                res = "저장 ERROR!!";
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("올바르지 않은 요청");
        }

        return res;
    }

    @ResponseBody
    @PostMapping(value = "/modify/board")
    public String updateBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        try {
            UserDto userDto = (UserDto) request.getSession().getAttribute("user");
            boardDto.setModifyId(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
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
    public HashMap deleteBoard(@PathVariable String idx, HttpSession session){
        HashMap hashMap = new HashMap();
        TypeCheck t = new TypeCheck();
        if(t.isNumeric(idx)){
            System.out.println(t.isNumeric(idx));
            System.out.println(boardService.countReply(idx));
            try{
                if(boardService.countReply(idx) == 0) {
                    hashMap.put("boardSeq",idx);
                    hashMap.put("userId", ((UserDto) session.getAttribute("user")).getUserId());
                    hashMap.put("deleteFlg",true);
                    boardService.deleteBoard(hashMap);
                }else {
                    hashMap.put("replyFlg",false);
                }
            }catch (Exception e){
                e.printStackTrace();
                hashMap.put("errorFlg", false);
            }
        } else{
            hashMap.put("typeFlg",false);
        }
        return hashMap;
    }
};
