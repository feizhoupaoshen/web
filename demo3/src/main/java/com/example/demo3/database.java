package com.example.demo3;

import java.sql.*;

public class database {
    Connection conn = null;

    public database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 这里可考虑从配置文件读取数据库连接信息
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypan?serverTimezone=GMT%2B8", "root", "cy123456");
    }

    public void showConnect() {
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("数据库连接失败");
            } else {
                System.out.println("数据库连接成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("检查连接时出现异常: " + e.getMessage());
        }
    }




    public void insert(String name, String pwd) throws SQLException {
        String sql = "insert into mypan.user (username, password) values (?,?)" +
                "";
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.setString(1, name);
        prep.setString(2, pwd);
        prep.executeUpdate();
    }
    public int select(String name, String pwd) throws SQLException{
            String sql = "select username, password from user WHERE username =? AND password =?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, name);
            prep.setString(2, pwd);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
