package main.java.controller.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.java.Constant;
import main.java.app.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static main.java.Constant.USELLHOUSEMANAGER_PAGE;


public class UserController implements Initializable {
    private static final int TYPE0 = 0;
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;
    private static final int TYPE4 = 4;


    private int currentType = -1;
    private ArrayList<Label> labels = new ArrayList<>();

    @FXML
    public Label mUsernameLabel;
    @FXML
    private Label mSellHouseManager;
    @FXML
    private Label mHouseSellProgress;
    @FXML
    private Label mQueryTargetHouse;
    @FXML
    private Label mMyFavoriteHouses;
    @FXML
    private Label mPersonalInformationManager;
    @FXML
    private Button mExitButton;
    @FXML
    public AnchorPane mContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mUsernameLabel.setText(Constant.NAME);
        labels.add(mSellHouseManager);
        labels.add(mHouseSellProgress);
        labels.add(mQueryTargetHouse);
        labels.add(mMyFavoriteHouses);
        labels.add(mPersonalInformationManager);

        setSelected(TYPE0, USELLHOUSEMANAGER_PAGE);
        currentType = TYPE0;
    }

    public void mSellHouseManagerClicked(MouseEvent mouseEvent) {
        setSelected(TYPE0, USELLHOUSEMANAGER_PAGE);
    }

    public void mHouseSellProgressClicked(MouseEvent mouseEvent) {
        setSelected(TYPE1, Constant.UHOUSESELLPROGRESS_PAGE);
    }

    public void mQueryTargetHouseClicked(MouseEvent mouseEvent) {
        setSelected(TYPE2, Constant.UQUERYTARGETHOUSE_PAGE);
    }

    public void mMyFavoriteHousesClicked(MouseEvent mouseEvent) {
        setSelected(TYPE3, Constant.UMYFAVORITEHOUSES_PAGE);
    }

    public void mPersonalInformationManagerClicked(MouseEvent mouseEvent) {
        setSelected(TYPE4, Constant.UPERSONALINFORMATIONMANAGER_PAGE);
    }

    public void mExitButtonClicked(ActionEvent actionEvent) {
        MainApp.getApp().toLogin();
    }

    private void setSelected(int type, String url) {
        if (type != currentType) {
            labels.get(type).setTextFill(Constant.SELECTED_COLOR);
            labels.get(type).setFont(Font.font("Timer New Roman", FontWeight.BOLD, 14));
            for (int i = 0; i < labels.size(); i++) {
                if (i != type) {
                    labels.get(i).setTextFill(Constant.UNSELECTED_COLOR);
                    labels.get(i).setFont(Font.font("Timer New Roman", FontWeight.NORMAL, 14));
                }
            }
            try {
                mContainer.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../" + url)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentType = type;
        }
    }


}
