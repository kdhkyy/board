package com.example.board.controller;

import com.example.board.domain.BoardDto;
import com.example.board.domain.UserDto;
import com.example.board.service.BoardService;
import com.example.util.Criteria;
import com.example.util.PageMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        boolean flg = false;
        if(idx != 0) flg = true;
        model.addAttribute("flg",flg);
        model.addAttribute("board",boardService.selectDetail(Math.toIntExact(idx)));
        return "board/form";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam(value = "idx") long idx, Model model, HttpSession session){
        boolean flg = false;
        BoardDto boardDto = boardService.selectDetail(Math.toIntExact(idx));
        if(!boardDto.getUserId().equals(((UserDto)session.getAttribute("user")).getUserId()) ) {
            flg = true;
        }
        System.out.println(flg);
        model.addAttribute("flg", flg);
        model.addAttribute("board",boardDto);
        return "board/form-modify";
    }

    @GetMapping("/reply")
    public String reply(@RequestParam(value = "idx") long idx, Model model){
        flg = true;
        model.addAttribute("flg", flg);
        model.addAttribute("board",boardService.selectDetail(Math.toIntExact(idx)));
        return "board/form-reply";
    }

    @GetMapping("/boardList")
    public String list(Model model, Criteria criteria) throws Exception{

        List<BoardDto> list = boardService.selectListPaging(criteria);

        PageMarker pageMarker = new PageMarker();
        pageMarker.setCri(criteria);
        pageMarker.setTotalCount(boardService.totalCount());

        model.addAttribute("boardList",list);
        model.addAttribute("pageMaker", pageMarker);

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< list.size(); i++){
            for(int j=0;j<list.get(i).getBoardDepth(); j++){

                builder.append("&nbsp;&nbsp;&nbsp;");
            }
            if(list.get(i).getBoardDepth() != 0) builder.append("&#9495;");
            list.get(i).setTitle(builder.append(list.get(i).getTitle()).toString());
            builder.setLength(0);
        }
        return "board/list";
    }
}
