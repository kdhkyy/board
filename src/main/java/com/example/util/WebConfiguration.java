//package com.example.util;
//
//import com.example.board.interceptor.SessionInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfiguration implements WebMvcConfigurer {
//    // 로그인 Interceptor
//
//    SessionInterceptor sessionInterceptor = new SessionInterceptor();
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(sessionInterceptor)
//                .addPathPatterns("/**/*.html");
//    }
//};
