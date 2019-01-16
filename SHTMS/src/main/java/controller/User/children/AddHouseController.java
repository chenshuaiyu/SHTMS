package main.java.controller.User.children;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.listener.AddHouseListener;
import main.java.Constant;
import main.java.bean.House;
import java.net.URL;
import java.util.ResourceBundle;


public class AddHouseController implements Initializable {
    @FXML
    public JFXTextField mProvinceTextField;
    @FXML
    public JFXTextField mCityTextField;
    @FXML
    public JFXTextField mCountyTextField;
    @FXML
    public JFXTextField mCommunityTextField;
    @FXML
    public JFXTextField mBuildingNoTextField;
    @FXML
    public JFXTextField mFloorTextField;
    @FXML
    public JFXTextField mNoTextField;
    @FXML
    public JFXTextField mAreaTextField;
    @FXML
    public JFXTextField mPriceTextField;
    @FXML
    public JFXTextField mPropertyCostTextField;
    @FXML
    public ChoiceBox mIsDecoratedTextField;
    @FXML
    public ChoiceBox mHasGarageTextField;
    @FXML
    public JFXTextField mRoomTextField;
    @FXML
    public JFXTextField mLivingRoomTextField;
    @FXML
    public Button mAddButton;
    @FXML
    public Button mCancelButton;

    private AddHouseListener listener;

    public void setAddHouseListener(AddHouseListener l) {
        listener = l;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mIsDecoratedTextField.setItems(Constant.ISDECORATED);
        mHasGarageTextField.setItems(Constant.HASGARAGE);
    }

    public void mAddButtonClicked(MouseEvent mouseEvent) {
        String province = mProvinceTextField.getText();
        String city = mCityTextField.getText();
        String county = mCountyTextField.getText();
        String community = mCommunityTextField.getText();
        String buindingNo = mBuildingNoTextField.getText();
        int floor = Integer.parseInt(mFloorTextField.getText());
        int no = Integer.parseInt(mNoTextField.getText());
        int area = Integer.parseInt(mAreaTextField.getText());
        float price = Float.parseFloat(mPriceTextField.getText());
        int propertyCost = Integer.parseInt(mPropertyCostTextField.getText());
        int index1 = mIsDecoratedTextField.getSelectionModel().getSelectedIndex();
        int index2 = mHasGarageTextField.getSelectionModel().getSelectedIndex();
        int isDecorated = (index1 == 0 ? 1 : 0);
        int hasGarage = (index2 == 0 ? 1 : 0);
        int room = Integer.parseInt(mRoomTextField.getText());
        int livingRoom = Integer.parseInt(mLivingRoomTextField.getText());

        listener.add(new House(0, province, city, county, community, buindingNo, floor, no, area, price, Constant.ID, propertyCost, isDecorated, hasGarage, room, livingRoom, 0));
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }


}
