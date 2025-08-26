package com.example.demo3;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/download")
public class download extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userDirPath = (String) req.getSession(false).getAttribute("userDirPath");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String fileName = req.getParameter("fileName");
        if(fileName == null || "".equals(fileName.trim())){
            resp.getWriter().write("请输入要下载的文件名");
            resp.getWriter().close();
            return;
        }
        File file = new File(userDirPath, fileName);
        if(file.exists() && file.isFile()){
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
            InputStream in = new FileInputStream(file);
            ServletOutputStream out = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            int len =0;
            while((len = in.read(bytes)) !=-1){
                out.write(bytes,0,len);
            }
            out.close();
            in.close();
        }else{
            resp.getWriter().write("文件不存在，请重试！");
            resp.getWriter().close();
        }
        resp.sendRedirect("show");
    }
}
