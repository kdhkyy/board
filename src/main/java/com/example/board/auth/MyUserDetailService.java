package com.example.board.auth;

//import com.example.board.domain.User;
//import com.example.board.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService { //implements UserDetailsService {
//
//    private final UserMapper userMapper;
//
//    @Autowired
//    public MyUserDetailService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userMapper.findByUserId(username);
//
//        System.out.println(user);
//
//        user.orElseThrow(()-> new UsernameNotFoundException("NOT FOUND: " + username));
//
//        return user.map(MyUserDetails::new).get();
//    }
};
