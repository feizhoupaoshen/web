package com.example.demo3;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/s2")
public class servlet2 extends HttpServlet {

    private String getWebAppPath() {
        return this.getServletContext().getRealPath("/");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String uname = req.getParameter("uname");
        String pwd   = req.getParameter("pwd");

        try {
            database reg = new database();
            reg.showConnect();
            if (reg.select(uname, pwd) == 1) {

                // 登录成功 → 存用户名
                HttpSession session = req.getSession();
                session.setAttribute("user", uname);

                // 2. 同时存用户目录路径
                String userDirPath = getWebAppPath() + "users" + File.separator + uname;
                session.setAttribute("userDirPath", userDirPath);

                req.getRequestDispatcher("show").forward(req, resp);
            } else {
                req.getRequestDispatcher("login.html").forward(req, resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}