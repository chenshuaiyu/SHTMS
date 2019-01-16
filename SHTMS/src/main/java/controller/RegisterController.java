package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.app.MainApp;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import static main.java.Constant.*;


public class RegisterController {
    @FXML
    private TextField mUsernameTextField;
    @FXML
    private PasswordField mPasswordField;
    @FXML
    private PasswordField mConfirmPasswordField;
    @FXML
    private TextField mNameTextField;
    @FXML
    private TextField mAgeTextField;
    @FXML
    private TextField mSexTextField;
    @FXML
    private TextField mIdentityNumberTextField;
    @FXML
    private TextField mTelTextField;
    @FXML
    private TextField mEmailTextField;
    @FXML
    private RadioButton mUserRadioButton;
    @FXML
    private RadioButton mIntermediaryRadioButton;
    @FXML
    private Button mRegisterButton;
    @FXML
    private Label mLoginLabel;
    @FXML
    private Label mResultLabel;

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
                int count = JDBCHelper.getInstance().getQueryCount(sql, objects);
                if (count == 0) {
                    sql = "INSERT INTO Uuser VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                    List<Object> params = Arrays.asList(username, name, sex, age, tel, email, identityNumber, password);
                    int result = JDBCHelper.getInstance().executeUpdate(sql, params);
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
