package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.java.app.MainApp;
import java.net.URL;
import java.util.ResourceBundle;


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
    public RadioButton mAdminRadioButton;
    @FXML
    public Button mRegisterButton;
    @FXML
    public Label mLoginLabel;

    private ToggleGroup group;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        mUserRadioButton.setToggleGroup(group);
        mAdminRadioButton.setToggleGroup(group);
        mUserRadioButton.setSelected(true);
    }

    public void mRegisterButtonClicked(MouseEvent mouseEvent) {

    }

    public void mLoginLabelClicked(MouseEvent mouseEvent) {
        MainApp.getApp().toLogin();
    }


}
