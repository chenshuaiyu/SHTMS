package main.java.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.bean.House;
import main.java.db.JDBCHelper;
import main.java.listener.LookHouseListener;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static main.java.Constant.HOUSEALREADYRESERVED;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/12 9:35
 */
public class LookHouseController implements Initializable {
    @FXML
    private JFXTextField mProvinceTextField;
    @FXML
    private JFXTextField mCityTextField;
    @FXML
    private JFXTextField mCountyTextField;
    @FXML
    private JFXTextField mCommunityTextField;
    @FXML
    private JFXTextField mBuildingNoTextField;
    @FXML
    private JFXTextField mFloorTextField;
    @FXML
    private JFXTextField mNoTextField;
    @FXML
    private JFXTextField mAreaTextField;
    @FXML
    private JFXTextField mPriceTextField;
    @FXML
    private JFXTextField mPropertyCostTextField;
    @FXML
    private JFXTextField mIsDecoratedTextField;
    @FXML
    private JFXTextField mHasGarageTextField;
    @FXML
    private JFXTextField mRoomTextField;
    @FXML
    private JFXTextField mLivingRoomTextField;
    @FXML
    private Button mLookHouseButton;
    @FXML
    private Button mCancelButton;

    private House house;
    private LookHouseListener listener;

    public void setLookHouseListener(LookHouseListener l) {
        listener = l;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        house = (House) resources.getObject("House");
        setValue();
        setEditable(false);

        String sql = "SELECT * FROM Reservationhouse WHERE Uuid = ? AND Hid = ?";
        List<Object> objects = Arrays.asList(Constant.ID, house.getId());
        int count = JDBCHelper.getsInstance().getQueryCount(sql, objects);
        if (count > 0) {
            setDisable();
        }
    }

    private void setDisable() {
        mLookHouseButton.setDisable(true);
        mLookHouseButton.setText(HOUSEALREADYRESERVED);
    }

    public void mLookHouseButtonClicked(MouseEvent mouseEvent) {
        listener.lookHouse(house);
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }

    private void setValue() {
        mProvinceTextField.setText(house.getProvince());
        mCityTextField.setText(house.getCity());
        mCountyTextField.setText(house.getCounty());
        mCommunityTextField.setText(house.getCommunityName());
        mBuildingNoTextField.setText(house.getBuildingNo());
        mFloorTextField.setText(house.getFloor() + "");
        mNoTextField.setText(house.getNo() + "");
        mAreaTextField.setText(house.getArea() + "");
        mPriceTextField.setText(house.getPrice() + "");
        mPropertyCostTextField.setText(house.getPropertyCost() + "");
        mIsDecoratedTextField.setText(Constant.ISDECORATED.get(house.getIsDecorated() == 1 ? 0 : 1));
        mHasGarageTextField.setText(Constant.HASGARAGE.get(house.getHasGarage() == 1 ? 0 : 1));
        mRoomTextField.setText(house.getRoomNum() + "");
        mLivingRoomTextField.setText(house.getLivingRoomNum() + "");
    }

    private void setEditable(boolean value) {
        mProvinceTextField.setEditable(value);
        mCityTextField.setEditable(value);
        mCountyTextField.setEditable(value);
        mCommunityTextField.setEditable(value);
        mBuildingNoTextField.setEditable(value);
        mFloorTextField.setEditable(value);
        mNoTextField.setEditable(value);
        mAreaTextField.setEditable(value);
        mPriceTextField.setEditable(value);
        mPropertyCostTextField.setEditable(value);
        mIsDecoratedTextField.setEditable(value);
        mHasGarageTextField.setEditable(value);
        mRoomTextField.setEditable(value);
        mLivingRoomTextField.setEditable(value);
    }

}
