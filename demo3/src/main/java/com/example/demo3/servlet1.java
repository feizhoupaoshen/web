package com.example.demo3;
import com.example.demo3.database;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;

@WebServlet("/s1")
public class servlet1 extends HttpServlet {

    // 得到 webapp 在磁盘上的绝对路径
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
            reg.insert(uname, pwd);

            // 1. 建用户目录：webapp/users/{uname}
            String userDirPath = getWebAppPath() + "users" + File.separator + uname;
            File userDir = new File(userDirPath);
            if (!userDir.exists()) {
                userDir.mkdirs();     // 如果父目录不存在也一起建
            }
            System.out.println("用户目录已创建：" + userDirPath);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
}