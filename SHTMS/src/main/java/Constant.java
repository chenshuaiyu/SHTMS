package main.java;

import javafx.scene.paint.Color;

public class Constant {
    //数据库
    public static final String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String URL = "jdbc:sqlserver://localhost:1433;database=SHTMS;";
    public static final String USER = "sa";
    public static final String PASSWORD = "1234";

    //角色类型
    public static final int USER_TYPE = 0;
    public static final int ADMIN_TYPE = 1;

    //登录结果提示
    public static final String IS_EMPTY = "用户名或密码为空";
    public static final String PASSWORDLESSSIX = "密码不可少于6位";


    //菜单栏选中颜色
    public static final Color SELECTED_COLOR= new Color(0, 0.0392, 0.502, 1);
    public static final Color UNSELECTED_COLOR= new Color(1, 1, 1, 1);

    //fxml
    public static final String USER_PAGE = "../../resourses/User.fxml";
    public static final String ADMIN_PAGE = "../../resourses/Admin.fxml";
    public static final String LOGIN_PAGE = "../../resourses/Login.fxml";
    public static final String REGISTER_PAGE = "../../resourses/Register.fxml";
    public static final String USELLHOUSE_PAGE = "../../resourses/USellHouse.fxml";

}
