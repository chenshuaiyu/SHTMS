package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class Constant {
    //当前登录角色
    public static int ID = -1;
    public static String NAME = "";
    public static int ROLE = -1;

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
    public static final String USERNAMEORPASSWORDISERROR = "用户名或密码错误";

    //注册结果提示
    public static final String USERNAMEEXIST = "用户名已存在";
    public static final String EMPTYINFORMATION = "信息不可为空";
    public static final String PASSWORDNOTSAME = "密码不一致";

    //房源状态
    public static final String HOUSEALREADYRESERVED = "已预约";
    public static final String HOUSEALREADYPAYDEPOSIT = "已付定金";
    public static final String HOUSEALREADYCOMPLETETRANSACTION = "完成交易";


    //菜单栏选中颜色
    public static final Color SELECTED_COLOR= new Color(0, 0.0392, 0.502, 1);
    public static final Color UNSELECTED_COLOR= new Color(1, 1, 1, 1);

    //fxml
    public static final String LOGIN_PAGE = "../../resources/fxml/Login.fxml";
    public static final String REGISTER_PAGE = "../../resources/fxml/Register.fxml";

    public static final String USER_PAGE = "../../resources/fxml/user/User.fxml";
    public static final String USELLHOUSEMANAGER_PAGE = "../../resources/fxml/user/USellHouseManager.fxml";
    public static final String UHOUSESELLPROGRESS_PAGE = "../../resources/fxml/user/UHouseSellProgress.fxml";
    public static final String UQUERYTARGETHOUSE_PAGE = "../../resources/fxml/user/UQueryTargetHouse.fxml";
    public static final String UMYFAVORITEHOUSES_PAGE = "../../resources/fxml/user/UMyFavoriteHouses.fxml";
    public static final String UPERSONALINFORMATIONMANAGER_PAGE = "../../resources/fxml/user/UPersonalInformationManager.fxml";

    public static final String INTERMEDIARY_PAGE = "../../resources/fxml/intermediary/Intermediary.fxml";
    public static final String IMANAGERHOUSEBUY_PAGE = "../../resources/fxml/intermediary/IManagerHouseBuy.fxml";
    public static final String IMANAGERTARGETHOUSE_PAGE = "../../resources/fxml/intermediary/IManagerTransactionInformation.fxml";
    public static final String IPERSONALINFORMATIONMANAGER_PAGE = "../../resources/fxml/intermediary/IPersonalInformationManager.fxml";

    //流程图的绝对路径
    public static final String DO1 = "file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\resources\\\\assets\\\\do1.png";
    public static final String DO2 = "file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\resources\\\\assets\\\\do2.png";
    public static final String UNDO1 = "file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\resources\\\\assets\\\\undo1.jpg";
    public static final String UNDO2 = "file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\resources\\\\assets\\\\undo2.jpg";


    //ChoiceBox的Item列表
    public static final ObservableList<String> SEXS = FXCollections.observableArrayList("男", "女");
    public static final ObservableList<Integer> AGES = FXCollections.observableArrayList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
            51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
            61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
            71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
            81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
            91, 92, 93, 94, 95, 96, 97, 98, 99, 100);
    public static final ObservableList<String> ISDECORATED = FXCollections.observableArrayList("是", "否");
    public static final ObservableList<String> HASGARAGE = FXCollections.observableArrayList("有", "无");
    public static final ObservableList<String> PRICE = FXCollections.observableArrayList("低于50万", "50-100万","高于100万");
    public static final ObservableList<String> AREA = FXCollections.observableArrayList("小于100㎡", "100-150㎡","大于150㎡");






}
