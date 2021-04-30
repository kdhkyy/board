package com.example.board.controller;

import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        if(request.getSession()!=null || !request.isRequestedSessionIdValid()){
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

};



