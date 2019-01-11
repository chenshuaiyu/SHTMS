package main.java;

import javafx.scene.paint.Color;

public class Constant {
    //当前登录角色
    public static String name = "";
    public static String role = "";

    //数据库
    public static final String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String URL = "jdbc:sqlserver://localhost:1433;database=SHTMS;";
    public static final String USER = "sa";
    public static final String PASSWORD = "1234";

    //角色类型
    public static final int USER_TYPE = 0;
    public static final int INTERMEDIARY_TYPE = 1;

    //登录结果提示
    public static final String IS_EMPTY = "用户名或密码为空";
    public static final String PASSWORDLESSSIX = "密码不可少于6位";


    //菜单栏选中颜色
    public static final Color SELECTED_COLOR= new Color(0, 0.0392, 0.502, 1);
    public static final Color UNSELECTED_COLOR= new Color(1, 1, 1, 1);

    //fxml
    public static final String LOGIN_PAGE = "../../resourses/fxml/Login.fxml";
    public static final String REGISTER_PAGE = "../../resourses/fxml/Register.fxml";

    public static final String USER_PAGE = "../../resourses/fxml/user/User.fxml";
    public static final String USELLHOUSEMANAGER_PAGE = "../../resourses/fxml/user/USellHouseManager.fxml";
    public static final String UHOUSESELLPROGRESS_PAGE = "../../resourses/fxml/user/UHouseSellProgress.fxml";
    public static final String UQUERYTARGETHOUSE_PAGE = "../../resourses/fxml/user/UQueryTargetHouse.fxml";
    public static final String UMYFAVORITEHOUSES_PAGE = "../../resourses/fxml/user/UMyFavoriteHouses.fxml";
    public static final String UPERSONALINFORMATIONMANAGER_PAGE = "../../resourses/fxml/user/UPersonalInformationManager.fxml";

    public static final String INTERMEDIARY_PAGE = "../../resourses/fxml/intermediary/Intermediary.fxml";
    public static final String IMANAGERHOUSEBUY_PAGE = "../../resourses/fxml/intermediary/IManagerHouseBuy.fxml";
    public static final String IMANAGERTARGETHOUSE_PAGE = "../../resourses/fxml/intermediary/IManagerTransactionInformation.fxml";
    public static final String IPERSONALINFORMATIONMANAGER_PAGE = "../../resourses/fxml/intermediary/IPersonalInformationManager.fxml";


}
