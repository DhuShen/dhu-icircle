package com.dhu.interceptor;

import com.dhu.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8;");
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert('您并未登录有用户账号！')</script>");
            response.setHeader("refresh", "0;URL=login");
            return false;
        }
        return true;
    }
}
