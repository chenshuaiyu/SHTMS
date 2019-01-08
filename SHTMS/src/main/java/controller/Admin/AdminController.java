package main.java.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.java.Constant;
import main.java.app.MainApp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminController implements Initializable {
    private static final int TYPE0 = 0;
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;


    private ArrayList<Label> labels = new ArrayList<>();

    @FXML
    private Label mSystemNotice;
    @FXML
    private Label mHouseInformationCheck;
    @FXML
    private Label mUserNoticePublic;
    @FXML
    private Label mPersonalInformationManager;
    @FXML
    private Button mExitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels.add(mSystemNotice);
        labels.add(mHouseInformationCheck);
        labels.add(mUserNoticePublic);
        labels.add(mPersonalInformationManager);

        setSelected(TYPE0);
    }

    public void mSystemNoticeClicked(MouseEvent mouseEvent) {
        setSelected(TYPE0);
    }

    public void mHouseInformationCheckClicked(MouseEvent mouseEvent) {
        setSelected(TYPE1);
    }

    public void mUserNoticePublicClicked(MouseEvent mouseEvent) {
        setSelected(TYPE2);
    }

    public void mmPersonalInformationManagerClicked(MouseEvent mouseEvent) {
        setSelected(TYPE3);
    }

    public void mExitButtonClicked(ActionEvent actionEvent) {
        MainApp.getApp().toLogin();
    }

    private void setSelected(int type) {
        labels.get(type).setTextFill(Constant.SELECTED_COLOR);
        labels.get(type).setFont(Font.font("Timer New Roman", FontWeight.BOLD, 14));
        for (int i = 0; i < labels.size(); i++) {
            if (i != type){
                labels.get(i).setTextFill(Constant.UNSELECTED_COLOR);
                labels.get(i).setFont(Font.font("Timer New Roman", FontWeight.NORMAL, 14));
            }
        }
    }



}
