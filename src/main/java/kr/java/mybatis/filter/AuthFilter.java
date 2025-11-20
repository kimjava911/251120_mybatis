package kr.java.mybatis.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// import jakarta.servlet.Filter;
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (session.getAttribute("username") == null) {
            res.sendRedirect("/login"); // login으로 이동
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse); // 이거 없으면 안 넘어감
    }
}
