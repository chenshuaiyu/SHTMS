package main.java.db;

import main.java.Constant;
import java.sql.*;
import static main.java.Constant.PASSWORD;
import static main.java.Constant.URL;
import static main.java.Constant.USER;


public class JDBCHelper {

    private static volatile JDBCHelper sInstance = null;
    private static Connection conn = null;

    private JDBCHelper() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            //加载驱动
            Class.forName(Constant.DRIVERNAME);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载驱动失败");
        }
    }

    /**
     * 单例模式
     *
     * @return sInstance
     */
    public static JDBCHelper getsInstance() {
        if (null == sInstance) {
            synchronized (JDBCHelper.class) {
                if (null == sInstance)
                    sInstance = new JDBCHelper();
            }
        }
        return sInstance;
    }

    /**
     * 连接数据库
     *
     * @return conn
     */
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param resultSet
     * @param statement
     * @param connection
     */
    public void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (null != resultSet) resultSet.close();
            if (null != statement) statement.close();
            if (null != connection) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接断开失败");
        }
    }

}
