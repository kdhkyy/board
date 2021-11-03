package com.example.board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@WebFilter(urlPatterns = "/board/*")
public class SessionFilter implements Filter {

    private List<String> whiteList;

    // 접근가능
    public SessionFilter(){
        whiteList = new ArrayList<String>();
        whiteList.add("/");
        whiteList.add("/static");
        whiteList.add("/css");
        whiteList.add("/login");
        whiteList.add("/actuator");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("init SessionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Custom Filtter Start");
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if(!whiteList.contains(uri)){
            HttpSession session = req.getSession();
            Optional optional = Optional.ofNullable(session.getAttribute("user"));
            System.out.println(optional);
            if(!optional.isPresent()){
                log.info("Custom Filtter End 1 ");
                ((HttpServletResponse)response).sendRedirect("/");
            }else{
                log.info("Custom Filtter End 2 ");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        log.info("destory SessionFilter");
    }


};
