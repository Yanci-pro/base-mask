package com;

import java.sql.*;

/**
 * @description: some desc
 * @author: Yanci
 * @date: 2022/6/16 11:31
 */
public class MysqlTest {
    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://127.0.0.1:3306/testdb?statementInterceptors=com.yanci.MaskResultSetScannerInterceptor";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from user ";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            String id = null;
            String name = null;
            String pwd = null;
            String phone = null;
            while (rs.next()) {
                //获取sno这列数据
                id = rs.getString("id");
                //获取name这列数据
                name = rs.getString("name");
                pwd = rs.getString("password");
                //获取phone这列数据
                phone = rs.getString("phone");
                //输出结果
                System.out.println(id + "\t," + name + "\t," + pwd + "\t," + "\t" + phone);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
