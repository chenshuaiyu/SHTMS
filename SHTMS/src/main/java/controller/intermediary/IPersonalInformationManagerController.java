package main.java.controller.intermediary;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.bean.User;
import java.net.URL;
import java.util.ResourceBundle;


public class IPersonalInformationManagerController implements Initializable {
    @FXML
    private JFXTextField mUsername;
    @FXML
    private JFXTextField mName;
    @FXML
    private JFXTextField mIdentityNumber;
    @FXML
    private ChoiceBox mSexChoiceBox;
    @FXML
    private ChoiceBox mAgeChoiceBox;
    @FXML
    private JFXTextField mSexTextField;
    @FXML
    private JFXTextField mAgeTextField;
    @FXML
    private JFXTextField mTel;
    @FXML
    private JFXTextField mEmail;
    @FXML
    private Button mEditButton;
    @FXML
    private Button mCompleteButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> sexs = FXCollections.observableArrayList("男", "女");
        ObservableList<Integer> ages = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        setEditable(false);
        User user = new User("昵称", "张三", "141031199601011110", "男", 20, "13546743074", "12345678@qq.com");
        mUsername.setText(user.getUserName());
        mName.setText(user.getName());
        mIdentityNumber.setText(user.getIdentityNumber());
        mSexChoiceBox.setItems(sexs);
        mSexTextField.setText("男");
        mAgeChoiceBox.setItems(ages);
        mAgeTextField.setText("20");
        mTel.setText(user.getTel());
        mEmail.setText(user.getEmail());

    }

    private void setEditable(boolean value) {
        mUsername.setEditable(value);
        mName.setEditable(value);
        mIdentityNumber.setEditable(value);
        mSexChoiceBox.setVisible(value);
        mSexTextField.setVisible(!value);
        mSexTextField.setEditable(value);
        mAgeChoiceBox.setVisible(value);
        mAgeTextField.setVisible(!value);
        mAgeTextField.setEditable(value);
        mTel.setEditable(value);
        mEmail.setEditable(value);
    }


    public void mEditButtonClicked(MouseEvent mouseEvent) {
        setEditable(true);
        mCompleteButton.setVisible(true);
        mEditButton.setVisible(false);
        mSexChoiceBox.setValue("男");
        mAgeChoiceBox.setValue(20);
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        setEditable(false);
        mCompleteButton.setVisible(false);
        mEditButton.setVisible(true);
    }
}
