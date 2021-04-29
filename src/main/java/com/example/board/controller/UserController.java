package com.example.board.controller;

import com.example.board.domain.UserDto;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String start(){
        return "index";
    }

    @PostMapping(value = "/login", produces = "application/json")
    public String login(Model model, UserDto userDto, HttpServletRequest request){
        String res = "/";

        Optional<UserDto> user = userService.findByIdAndPasswd(userDto);
        if(Optional.ofNullable(user.map(UserDto::getName)).isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("user",user.get());
            res = "board/boardList";
        }
        return "redirect:"+res;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        if(request.getSession()==null || !request.isRequestedSessionIdValid()){
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

};



