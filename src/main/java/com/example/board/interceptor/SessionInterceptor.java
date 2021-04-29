//package com.example.board.interceptor;
//
//import com.example.board.domain.UserDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Slf4j
//@Component
//public class SessionInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("preHandle Start!!");
//        HttpSession session = request.getSession();
//        UserDto userDto = (UserDto) session.getAttribute("user");
//        if(ObjectUtils.isEmpty(userDto)){
//            response.sendRedirect("/");
//            log.info("preHandle NOT SESSION!!");
//            return false;
//        }else{
//            session.setMaxInactiveInterval(10*60);
//            log.info("preHandle SESSION ADD!!");
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//};
