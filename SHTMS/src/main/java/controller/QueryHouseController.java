package main.java.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.db.JDBCHelper;
import main.java.listener.QueryHouseListener;
import main.java.bean.House;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class QueryHouseController implements Initializable {
    @FXML
    public JFXTextField mProvinceTextField;
    @FXML
    public JFXTextField mCityTextField;
    @FXML
    public JFXTextField mCountyTextField;
    @FXML
    public JFXTextField mCommunityTextField;
    @FXML
    public JFXTextField mFloorTextField;
    @FXML
    public ChoiceBox mAreaChoiceBox;
    @FXML
    public ChoiceBox mPriceChoiceBox;
    @FXML
    public JFXTextField mRoomTextField;
    @FXML
    public JFXTextField mLivingRoomTextField;
    @FXML
    public Button mQueryButton;
    @FXML
    public Button mCancelButton;

    private QueryHouseListener listener;

    public void setQueryHouseListener(QueryHouseListener l) {
        listener = l;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mAreaChoiceBox.setItems(Constant.AREA);
        mPriceChoiceBox.setItems(Constant.PRICE);

    }

    public void mQueryButtonClicked(MouseEvent mouseEvent) {
        String province = mProvinceTextField.getText();
        String city = mCityTextField.getText();
        String county = mCountyTextField.getText();
        String community = mCommunityTextField.getText();
        String floor = mFloorTextField.getText();
        int area = mAreaChoiceBox.getSelectionModel().getSelectedIndex();
        int price = mPriceChoiceBox.getSelectionModel().getSelectedIndex();
        String romm = mRoomTextField.getText();
        String livingRoom = mLivingRoomTextField.getText();

        String sql = "";
        List<Object> objects = Arrays.asList();

        listener.query(sql, objects);
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }
}
