package main.java.controller.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.Constant;
import main.java.bean.House;
import main.java.controller.UpdateHouseController;
import main.java.db.JDBCHelper;
import main.java.listener.ListViewListener;
import main.java.utils.ListViewHelper;
import sun.net.www.content.audio.x_aiff;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class UMyFavoriteHousesController implements Initializable {
    @FXML
    private ListView mListView;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private Button mLookHouseButton;
    @FXML
    private Button mPayDepositButton;
    @FXML
    private Button mCompleteButton;

    private ListViewHelper listViewHelper;

    private House house;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mLookHouseButton.setDisable(true);
        mPayDepositButton.setDisable(true);
        mCompleteButton.setDisable(true);

        listViewHelper = new ListViewHelper(mListView);
        listViewHelper.setListener("SELECT * FROM House,Reservationhouse WHERE Reservationhouse.Uuid = ? AND House.Hid = Reservationhouse.Hid;", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                house = item;
                List<Object> rObjects = Arrays.asList(item.getId(), Constant.ID);
                ResultSet rResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Reservationhouse WHERE Reservationhouse.Hid = ? AND Reservationhouse.Hid = House.Hid AND House.Iid = ?;", rObjects);
                List<Object> pObjects = Arrays.asList(item.getId(), Constant.ID);
                ResultSet pResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Paydeposit WHERE Paydeposit.Hid = ? AND Paydeposit.Hid = House.Hid AND House.Iid = ?;", pObjects);
                List<Object> cObjects = Arrays.asList(item.getId(), Constant.ID);
                ResultSet cResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Completetransaction WHERE Completetransaction.Hid = ? AND Completetransaction.Hid = House.Hid AND House.Iid = ?;", cObjects);

                int rAgree = -1, pAgree = -1, cAgree = -1;
                try {
                    if (rResultSet.next())
                        rAgree = rResultSet.getInt(5);
                    if (pResultSet.next())
                        pAgree = rResultSet.getInt(7);
                    if (rResultSet.next())
                        cAgree = cResultSet.getInt(8);

                    System.out.println(rAgree);
                    System.out.println(pAgree);
                    System.out.println(cAgree);

                    setFlag(rAgree, pAgree, cAgree);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void mLookHouseButtonClicked(MouseEvent mouseEvent) {
        mLookHouseButton.setDisable(true);
        mLookHouseButton.setText("已看房");
    }

    public void mPayDepositButtonClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("预约信息");
        alert.setHeaderText(null);
        alert.setContentText("已预约付定金");
        alert.showAndWait();
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("预约信息");
        alert.setHeaderText(null);
        alert.setContentText("已预约付全款");
        alert.showAndWait();
    }

    public void setFlag(int rAgree, int pAgree, int cAgree) {
        if (rAgree == 1) {
            image1.setImage(new Image(Constant.DO1));
            mLookHouseButton.setDisable(true);
        } else if (rAgree == 0) {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(true);
        } else {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(false);
        }

        if (pAgree == 1) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
            mPayDepositButton.setDisable(true);
        } else if (pAgree == 0) {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(true);
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(false);
        }

        if (cAgree == 1) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
            mCompleteButton.setDisable(true);
        } else if (cAgree == 0) {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(true);
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(false);
        }
    }

}
