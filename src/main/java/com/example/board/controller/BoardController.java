package com.example.board.controller;

import com.example.board.domain.BoardDto;
import com.example.board.domain.UserDto;
import com.example.board.service.BoardService;
import com.example.util.Criteria;
import com.example.util.PageMarker;
import com.example.util.TypeCheck;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")

public class BoardController {


    private final BoardService boardService;
    boolean flg = false;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    TypeCheck typeCheck = new TypeCheck();

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") String idx, Model model) {
        flg = false;
        BoardDto bto = boardService.selectDetail(idx);
        if(typeCheck.isNumeric(idx) || !StringUtils.isBlank(bto.getContent())){
            if(Long.parseLong(idx) != 0) flg = true;
            model.addAttribute("flg",flg);
            model.addAttribute("board",bto);
            return "board/form";
        }else{
            throw new IllegalStateException("올바르지 않은 요청");
        }
    }

    @GetMapping("/modify")
    public String modify(@RequestParam(value = "idx") String idx, Model model, HttpSession session){
        flg = false;
        if(typeCheck.isNumeric(idx)){
            BoardDto boardDto = boardService.selectDetail(idx);
            if(!boardDto.getUserId().equals(((UserDto)session.getAttribute("user")).getUserId()) ) {
                flg = true;
            }
            model.addAttribute("flg", flg);
            model.addAttribute("board",boardDto);
        }else{
            throw new IllegalStateException("올바르지 않은 요청");
        }
        return "board/form-modify";
    }

    @GetMapping("/reply")
    public String reply(@RequestParam(value = "idx") String idx, Model model){
        flg = true;
        if(typeCheck.isNumeric(idx)){
            model.addAttribute("flg", flg);
            model.addAttribute("board",boardService.selectDetail(idx));
        }else{
            throw new IllegalStateException("올바르지 않은 요청");
        }
        return "board/form-reply";
    }

    @GetMapping("/boardList")
    public String list(Model model, Criteria criteria) {

        if(typeCheck.isNumeric(String.valueOf(criteria.getPage()))){
            PageMarker pageMarker = new PageMarker();
            pageMarker.setCri(criteria);
            pageMarker.setTotalCount(boardService.totalCount());
            if(criteria.getPage() <= pageMarker.getEndPage()){
                List<BoardDto> list = boardService.selectListPaging(criteria);
                model.addAttribute("boardList",list);
                model.addAttribute("pageMaker", pageMarker);
                System.out.println(criteria);
                System.out.println(pageMarker);
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i< list.size(); i++){
                    for(int j=0;j<Integer.parseInt(list.get(i).getBoardDepth()); j++){

                        builder.append("&nbsp;&nbsp;&nbsp;");
                    }
                    if(Long.parseLong(list.get(i).getBoardDepth()) != 0) builder.append("&#9495;");
                    list.get(i).setTitle(builder.append(list.get(i).getTitle()).toString());
                    builder.setLength(0);
                }
            }
        } else{
            throw new IllegalStateException("올바르지 않은 요청");
        }
        
        return "board/list";
    }
}
