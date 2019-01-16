package main.java.db;

import main.java.Constant;
import java.sql.*;
import java.util.List;
import static main.java.Constant.PASSWORD;
import static main.java.Constant.URL;
import static main.java.Constant.USER;


public class JDBCHelper {

    private static volatile JDBCHelper sInstance = null;
    private static Connection conn = null;
    private static PreparedStatement preparedStatement = null;

    private JDBCHelper() {
    }

    /**
     * 加载驱动，连接数据库
     */
    private void init() {
        try {
            //加载驱动
            Class.forName(Constant.DRIVERNAME);
//            System.out.println("加载驱动成功");
            conn = getConnection();
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
    public static JDBCHelper getInstance() {
        if (null == sInstance) {
            synchronized (JDBCHelper.class) {
                if (null == sInstance)
                    sInstance = new JDBCHelper();
            }
        }
        sInstance.init();
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
//            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
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

    public int executeUpdate(String sql, List<Object> objects) {
        int result = -1;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (null != objects && objects.size()!= 0) {
                for (int i = 0; i < objects.size(); i++) {
                    preparedStatement.setObject(i + 1, objects.get(i));
                }
            }
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet executeQuery(String sql, List<Object> objects) {
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (null != objects && objects.size()!= 0) {
                for (int i = 0; i < objects.size(); i++) {
                    preparedStatement.setObject(i + 1, objects.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int getQueryCount(String sql, List<Object> objects) {
        int count = 0;
        ResultSet resultSet = null;
        try {
            resultSet = executeQuery(sql, objects);
            if (resultSet != null) {
                while (resultSet.next()){
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
