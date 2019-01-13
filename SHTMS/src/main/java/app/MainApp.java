package main.java.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Constant;

import java.io.IOException;

public class MainApp extends Application {

    private static MainApp app;
    private Stage stage;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        app = this;
        stage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("../../resources/fxml/Login.fxml"));
        primaryStage.setTitle("二手房交易管理系统");
        primaryStage.setScene(new Scene(root, 720, 405));
//        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static MainApp getApp(){
        return app;
    }

    public void close(){
        stage.close();
    }

    public void toUser() {
        toPage(Constant.USER_PAGE, 820, 498);
    }

    public void toIntermediary() {
        toPage(Constant.INTERMEDIARY_PAGE, 820, 498);
    }

    public void toLogin() {
        toPage(Constant.LOGIN_PAGE, 720, 405);
    }

    public void toRegister() {
        toPage(Constant.REGISTER_PAGE, 485, 515);
    }

    private void toPage(String fxml, int width, int height) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root, width, height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
