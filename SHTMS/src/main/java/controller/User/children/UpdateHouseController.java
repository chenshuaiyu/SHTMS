package main.java.controller.User.children;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.listener.UpdateHouseListener;
import main.java.bean.House;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateHouseController implements Initializable {
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
    private ChoiceBox mIsDecoratedChoiceBox;
    @FXML
    private ChoiceBox mHasGarageChoiceBox;
    @FXML
    private JFXTextField mIsDecoratedTextField;
    @FXML
    private JFXTextField mHasGarageTextField;
    @FXML
    private JFXTextField mRoomTextField;
    @FXML
    private JFXTextField mLivingRoomTextField;
    @FXML
    private Button mUpdateButton;
    @FXML
    private Button mCompleteButton;
    @FXML
    public Button mCancelButton;

    private House house;

    private UpdateHouseListener listener;

    public void setUpdateHouseListener(UpdateHouseListener l) {
        listener = l;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEditable(false);

        mIsDecoratedChoiceBox.setItems(Constant.ISDECORATED);
        mHasGarageChoiceBox.setItems(Constant.HASGARAGE);

        house = (House) resources.getObject("House");
        setValue();
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

        mIsDecoratedChoiceBox.setVisible(value);
        mIsDecoratedTextField.setVisible(!value);
        mIsDecoratedTextField.setEditable(value);

        mHasGarageChoiceBox.setVisible(value);
        mHasGarageTextField.setVisible(!value);
        mHasGarageTextField.setEditable(value);

        mRoomTextField.setEditable(value);
        mLivingRoomTextField.setEditable(value);

        mCompleteButton.setVisible(value);
        mCancelButton.setVisible(value);
        mUpdateButton.setVisible(!value);
    }

    public void mUpdateButtonClicked(MouseEvent mouseEvent) {
        setEditable(true);
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        String province = mProvinceTextField.getText();
        String city = mCityTextField.getText();
        String county = mCountyTextField.getText();
        String community = mCommunityTextField.getText();
        String buildingNo = mBuildingNoTextField.getText();
        int floor = Integer.parseInt(mFloorTextField.getText());
        int no = Integer.parseInt(mNoTextField.getText());
        int area = Integer.parseInt(mAreaTextField.getText());
        float price = Float.parseFloat(mPriceTextField.getText());
        int propertyCost = Integer.parseInt(mPropertyCostTextField.getText());

        int isDecorated = mIsDecoratedChoiceBox.getSelectionModel().getSelectedIndex() == 0 ? 1 : 0;
        int hasGarage = mHasGarageChoiceBox.getSelectionModel().getSelectedIndex() == 0 ? 1 : 0;

        int room = Integer.parseInt(mRoomTextField.getText());
        int livingRoom = Integer.parseInt(mLivingRoomTextField.getText());

        listener.complete(new House(house.getId(), province, city, county, community, buildingNo, floor, no, area, price, Constant.ID, propertyCost, isDecorated, hasGarage, room, livingRoom, 0));
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
        mIsDecoratedChoiceBox.getSelectionModel().select(house.getIsDecorated() == 1 ? 0 : 1);
        mHasGarageTextField.setText(Constant.HASGARAGE.get(house.getHasGarage() == 1 ? 0 : 1));
        mHasGarageChoiceBox.getSelectionModel().select(house.getHasGarage() == 1 ? 0 : 1);

        mRoomTextField.setText(house.getRoomNum() + "");
        mLivingRoomTextField.setText(house.getLivingRoomNum() + "");
    }
}
