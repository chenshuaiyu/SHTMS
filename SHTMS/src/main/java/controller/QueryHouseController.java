package main.java.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.QueryHouseListener;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/11 14:06
 */
public class QueryHouseController {
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
    public ChoiceBox mAreaTextField;
    @FXML
    public ChoiceBox mPriceTextField;
    @FXML
    public JFXTextField mRoomTextField;
    @FXML
    public JFXTextField mLivingRoomTextField;
    @FXML
    public Button mQueryButton;
    @FXML
    public Button mCancelButton;

    private static QueryHouseListener listener;

    public static void setQueryHouse(QueryHouseListener l){
        listener = l;
    }

    public void mQueryButtonClicked(MouseEvent mouseEvent) {
        String province = mProvinceTextField.getText();
        String city = mCityTextField.getText();
        String county = mCountyTextField.getText();
        String community = mCommunityTextField.getText();
        String floor = mFloorTextField.getText();
//        mAreaTextField;
//        mPriceTextField;
        String romm = mRoomTextField.getText();
        String livingRoom = mLivingRoomTextField.getText();

        listener.query(province);

    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }
}
