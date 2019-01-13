package main.java.controller.intermediary;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.bean.User;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static main.java.Constant.AGES;
import static main.java.Constant.SEXS;


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
    @FXML
    private Button mCancelButton;

    private ResultSet resultSet;

    private String name;
    private String sex;
    private int age;
    private String tel;
    private String email;
    private String identityNum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEditable(false);
        mSexChoiceBox.setItems(SEXS);
        mAgeChoiceBox.setItems(AGES);

        String sql = "SELECT * FROM Intermediary WHERE Iusername = ? ";
        List<Object> objects = Arrays.asList(Constant.NAME);
        resultSet = JDBCHelper.getsInstance().executeQuery(sql, objects);
        int count = JDBCHelper.getsInstance().getQueryCount(sql, objects);
        try {
            resultSet.next();
            getData(resultSet);
            setValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mEditButtonClicked(MouseEvent mouseEvent) {
        setEditable(true);
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        setEditable(false);
        name = mName.getText();
        identityNum = mIdentityNumber.getText();
        sex = Constant.SEXS.get(mSexChoiceBox.getSelectionModel().getSelectedIndex());
        age = mAgeChoiceBox.getSelectionModel().getSelectedIndex() + 1;
        tel = mTel.getText();
        email = mEmail.getText();

        String sql = "UPDATE Intermediary SET Iname = ?, Isex = ?, Iage = ?, Itel = ?, Iemail = ?, Iidentity_num = ? WHERE Iusername = ? ";
        List<Object> objects = Arrays.asList(name, sex, age, tel, email, identityNum, Constant.NAME);
        int result = JDBCHelper.getsInstance().executeUpdate(sql, objects);

        if (result > 0) {
            setValue();
            AlertUtil.alert("修改信息", "修改成功");
        } else {
            getData(resultSet);
            setValue();
            AlertUtil.alert("修改信息", "修改失败");
        }
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        setEditable(false);
        setValue();
    }

    private void getData(ResultSet resultSet) {
        try {
            name = resultSet.getString(3);
            sex = resultSet.getString(4);
            age = resultSet.getInt(5);
            tel = resultSet.getString(6);
            email = resultSet.getString(7);
            identityNum = resultSet.getString(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        mCompleteButton.setVisible(value);
        mCancelButton.setVisible(value);
        mEditButton.setVisible(!value);
    }

    private void setValue() {
        mUsername.setText(Constant.NAME);
        mName.setText(name);
        mIdentityNumber.setText(identityNum);
        mSexChoiceBox.getSelectionModel().select((sex.equals("男") ? 0 : 1));
        mSexTextField.setText(sex);
        mAgeChoiceBox.getSelectionModel().select(age - 1);
        mAgeTextField.setText(age + "");
        mTel.setText(tel);
        mEmail.setText(email);
    }
}
