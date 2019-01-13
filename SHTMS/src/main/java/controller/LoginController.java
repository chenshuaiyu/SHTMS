package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.app.MainApp;
import main.java.db.JDBCHelper;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static main.java.Constant.*;


public class LoginController implements Initializable {
    @FXML
    private TextField mUsernameTextField;
    @FXML
    private TextField mPasswordField;
    @FXML
    private RadioButton mUserRadioButton;
    @FXML
    private RadioButton mIntermediaryRadioButton;
    @FXML
    private Label mResultLabel;

    private ToggleGroup group;
    private int roleType = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setResult(false, "");
        group = new ToggleGroup();
        mUserRadioButton.setToggleGroup(group);
        mIntermediaryRadioButton.setToggleGroup(group);
        mUserRadioButton.setSelected(true);
    }

    public void onLoginButtonClicked(ActionEvent actionEvent) {
        String username = mUsernameTextField.getText();
        String password = mPasswordField.getText();
        roleType = mUserRadioButton.isSelected() ? USER_TYPE : INTERMEDIARY_TYPE;
        if (username.length() == 0 || password.length() == 0) {
            setResult(true, Constant.IS_EMPTY);
        } else if (password.length() < 6) {
            setResult(true, Constant.PASSWORDLESSSIX);
        } else {
            String sql = null;
            List<Object> objects = null;
            try {
                if (roleType == USER_TYPE) {
                    sql = "SELECT * FROM Uuser WHERE Uusername = ? AND Upassword = ?";
                    objects = Arrays.asList(username, password);
                    ResultSet resultSet = JDBCHelper.getsInstance().executeQuery(sql, objects);
                    if (resultSet.next()) {
                        Constant.ID = resultSet.getInt(1);
                        Constant.NAME = username;
                        Constant.ROLE = USER_TYPE;
                        MainApp.getApp().toUser();
                    } else
                        setResult(true, USERNAMEORPASSWORDISERROR);
                } else {
                    sql = "SELECT * FROM Intermediary WHERE Iusername = ? AND Ipassword = ?";
                    objects = Arrays.asList(username, password);
                    ResultSet resultSet = JDBCHelper.getsInstance().executeQuery(sql, objects);
                    if (resultSet.next()) {
                        Constant.ID = resultSet.getInt(1);
                        Constant.NAME = username;
                        Constant.ROLE = INTERMEDIARY_TYPE;
                        MainApp.getApp().toIntermediary();
                    } else
                        setResult(true, USERNAMEORPASSWORDISERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setResult(boolean flag, String result) {
        mResultLabel.setVisible(flag);
        mResultLabel.setText(result);
    }

    public void mRegisterLabelClicked(MouseEvent mouseEvent) {
        MainApp.getApp().toRegister();
    }
}
