package com.zxyun.common.db.mysql.factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/14 16:44
 */
public class MysqlConnection {
    private String url = "jdbc:mysql://172.18.13.173:3306/information_schema?useUnicode=true&zeroDateTimeBehavior=convertToNull";
    private String userName = "dev_jibiandang";
    private String password = "dev_jibiandang@123456";

    private Connection connection;

    private MysqlConnection () throws Exception {
        //加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url,userName,password);
    }

    private Connection getConnection () {
        return connection;
    }

    public static Connection getInstance () throws Exception {
        return MysqlConnectionHolder.getConnection().getConnection();
    }

    public static class MysqlConnectionHolder {
        public static MysqlConnection getConnection () throws Exception {
            return new MysqlConnection();
        }
    }
}
