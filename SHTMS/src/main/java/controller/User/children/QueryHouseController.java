package main.java.controller.User.children;

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
        String room = mRoomTextField.getText();
        String livingRoom = mLivingRoomTextField.getText();


        int flag = 0;

        //手动组合SQL语句
        String sql = "SELECT * FROM House WHERE";
        if (province.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hprovince = \'" + province + "\'";
            flag = 1;
        }
        if (city.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hcity = \'" + city + "\'";
            flag = 1;
        }
        if (county.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hcounty = \'" + county + "\'";
            flag = 1;
        }
        if (community.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hcommunity = \'" + community + "\'";
            flag = 1;
        }
        if (floor.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hfloor = " + floor;
            flag = 1;
        }
        if (room.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hroom_num = " + room;
            flag = 1;
        }
        if (livingRoom.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + "Hliving_room_num = " + livingRoom;
            flag = 1;
        }

//        "小于100㎡", "100-150㎡","大于150㎡"
        String areaSQl = "";
        //area
        switch (area){
            case -1:
                break;
            case 0:
                areaSQl += "Harea <= 100 ";
                break;
            case 1:
                areaSQl += "Harea >= 100 AND Harea <= 150 ";
                break;
            case 2:
                areaSQl += "Harea >= 150 ";
                break;
            default:
                break;
        }
        if (areaSQl.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + areaSQl;
            flag = 1;
        }

//        "低于50万", "50-100万","高于100万"
        //price
        String priceSQl = "";
        switch (price){
            case -1:
                break;
            case 0:
                priceSQl += "Hprice <= 50 ";
                break;
            case 1:
                priceSQl += "Hprice >= 50 AND Hprice <= 100 ";
                break;
            case 2:
                priceSQl += "Hprice >= 100 ";
                break;
            default:
                break;
        }
        if (priceSQl.length() != 0) {
            sql += (flag == 1 ? " AND " : " ") + priceSQl;
            flag = 1;
        }
        if (flag != 0)
            sql += " AND ";

        listener.query(sql);
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }
}
