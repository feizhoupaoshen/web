package com.example.demo3;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/logout")
public class logout extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. 销毁当前会话
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 2. 跳转到登录页面
        resp.sendRedirect(req.getContextPath() + "login.html");
    }
}
