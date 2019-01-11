package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.app.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import static main.java.Constant.INTERMEDIARY_TYPE;
import static main.java.Constant.USER_TYPE;


public class LoginController implements Initializable{

    @FXML
    private Button mLoginButton;
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
    @FXML
    private Label mRegisterLabel;

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
        } else if (password.length() < 6){
            setResult(true, Constant.PASSWORDLESSSIX);
        } else {
            if (roleType == USER_TYPE)
                MainApp.getApp().toUser();
            else
                MainApp.getApp().toIntermediary();
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
