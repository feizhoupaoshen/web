package com.example.demo3;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession(false);
        String userDirPath = (String) session.getAttribute("userDirPath");

        Part myfile = req.getPart("myfile");
        String fileName = req.getParameter("filename");
        if (fileName == null || fileName.trim().isEmpty()) {
            fileName = myfile.getSubmittedFileName();
        }

        myfile.write(userDirPath + File.separator + fileName);
        resp.sendRedirect("show");
    }
}
