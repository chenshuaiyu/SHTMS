package main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.util.ResourceBundle;
import static main.java.Constant.ADMIN_TYPE;
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
    private RadioButton mAdminRadioButton;

    private ToggleGroup group;
    private int roleType = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        mUserRadioButton.setToggleGroup(group);
        mAdminRadioButton.setToggleGroup(group);
        mUserRadioButton.setSelected(true);
    }

    public void onLoginButtonClicked(ActionEvent actionEvent) {
        String username = mUsernameTextField.getText();
        String password = mPasswordField.getText();
        roleType = mUserRadioButton.isSelected() ? USER_TYPE : ADMIN_TYPE;


    }


}
