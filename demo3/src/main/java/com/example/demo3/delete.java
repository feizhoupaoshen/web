package com.example.demo3;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
@WebServlet("/delete")
public class delete extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userDirPath = (String) req.getSession(false).getAttribute("userDirPath");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取用户输入的文件名
        String fileName = req.getParameter("fileName");
        if (fileName == null || "".equals(fileName.trim())) {
            resp.getWriter().write("请输入要删除的文件");
            resp.getWriter().close();
            return;
        }
        // 构建文件路径
        File file = new File(userDirPath, fileName);
        // 检查文件是否存在并尝试删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    resp.getWriter().write("1");
                    resp.getWriter().close();
                } else {
                    resp.getWriter().write("2");
                    resp.getWriter().close();
                }
            } else {
                resp.getWriter().write("3");
                resp.getWriter().close();
            }
    }
}
