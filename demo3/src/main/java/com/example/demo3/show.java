package com.example.demo3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet("/show")
public class show extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
            return;
        }

        // 3. 取出用户目录
        String userDirPath = (String) session.getAttribute("userDirPath");
        File dir = new File(userDirPath);
        if (!dir.exists()) {
            dir.mkdirs();          // 万一被手动删掉，自动重建
        }

        File[] files = dir.listFiles();
        req.setAttribute("allfiles", files);
        super.processTemplate("file1", req, resp);
    }
}