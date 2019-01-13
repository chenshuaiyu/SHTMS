package main.java.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.app.MainApp;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static main.java.Constant.*;


public class RegisterController implements Initializable {
    @FXML
    public TextField mUsernameTextField;
    @FXML
    public PasswordField mPasswordField;
    @FXML
    public PasswordField mConfirmPasswordField;
    @FXML
    public TextField mNameTextField;
    @FXML
    public TextField mAgeTextField;
    @FXML
    public TextField mSexTextField;
    @FXML
    public TextField mIdentityNumberTextField;
    @FXML
    public TextField mTelTextField;
    @FXML
    public TextField mEmailTextField;
    @FXML
    public RadioButton mUserRadioButton;
    @FXML
    public RadioButton mIntermediaryRadioButton;
    @FXML
    public Button mRegisterButton;
    @FXML
    public Label mLoginLabel;
    @FXML
    public Label mResultLabel;

    private ToggleGroup group;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
//        mUserRadioButton.setToggleGroup(group);
//        mIntermediaryRadioButton.setToggleGroup(group);
//        mUserRadioButton.setSelected(true);
    }

    public void mRegisterButtonClicked(MouseEvent mouseEvent) {
        String username = mUsernameTextField.getText();
        String password = mPasswordField.getText();
        String confirmPassword = mConfirmPasswordField.getText();
        String name = mNameTextField.getText();
        String age = mAgeTextField.getText();
        String sex = mSexTextField.getText();
        String identityNumber = mIdentityNumberTextField.getText();
        String tel = mTelTextField.getText();
        String email = mEmailTextField.getText();

        if (username.length() == 0 || password.length() == 0
                || confirmPassword.length() == 0 || name.length() == 0
                || age.length() == 0 || sex.length() == 0
                || identityNumber.length() == 0 || tel.length() == 0
                || email.length() == 0) {
            setResult(true, EMPTYINFORMATION);
        } else if (password.length() < 6) {
            setResult(true, PASSWORDLESSSIX);
        } else if (!password.equals(confirmPassword)) {
            setResult(true, PASSWORDNOTSAME);
        } else {
            String sql = null;
            List<Object> objects = null;
            ResultSet resultSet = null;

            try {
                sql = "SELECT * FROM Uuser WHERE Uusername = ?";
                objects = Arrays.asList(username);
                int count = JDBCHelper.getsInstance().getQueryCount(sql, objects);
                if (count == 0) {
                    sql = "INSERT INTO Uuser VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                    List<Object> params = Arrays.asList(username, name, sex, age, tel, email, identityNumber, password);
                    int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
                    if (result > 0) {
                        MainApp.getApp().toLogin();
                        AlertUtil.alert("注册信息", "注册成功");
                    }
                } else {
                    setResult(true, USERNAMEEXIST);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mLoginLabelClicked(MouseEvent mouseEvent) {
        MainApp.getApp().toLogin();
    }

    private void setResult(boolean flag, String result) {
        mResultLabel.setVisible(flag);
        mResultLabel.setText(result);
    }

}
