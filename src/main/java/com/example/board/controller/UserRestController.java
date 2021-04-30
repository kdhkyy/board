package com.example.board.controller;

import com.example.board.domain.UserDto;
import com.example.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@RestController
@Slf4j
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public HashMap login(@RequestBody UserDto userDto, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("=-======= this is login !!! =====");
        HashMap map = new HashMap();

        UserDto temp = userService.findById(userDto);
        if(Optional.ofNullable(temp).isPresent()){
            map.put("idCheck" , true);
            if(userDto.getPasswd().equals(temp.getPasswd())) {
                HttpSession session = request.getSession();
                session.setAttribute("user",temp);
                map.put("passwdCheck", true);
            }
            else map.put("passwdCheck", false);
        }else{
            map.put("idCheck" , false);
        }
        return map;
    }

};
