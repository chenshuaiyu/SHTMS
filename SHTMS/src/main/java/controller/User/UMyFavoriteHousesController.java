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
import main.java.utils.AlertUtil;
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
    @FXML
    private Label mLookHouseLabel;
    @FXML
    private Label mPayDepositLabel;
    @FXML
    private Label mCompleteLabel;

    private ListViewHelper listViewHelper;

    private House house;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mLookHouseButton.setDisable(true);
        mPayDepositButton.setDisable(true);
        mCompleteButton.setDisable(true);

        listViewHelper = new ListViewHelper(mListView);
        listViewHelper.setListener("SELECT * FROM House, Reservationhouse WHERE Reservationhouse.Uuid = ? AND House.Hid = Reservationhouse.Hid;", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                house = item;
                List<Object> rObjects = Arrays.asList(Constant.ID, item.getId());
                ResultSet rResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Ragree FROM House, Reservationhouse WHERE Reservationhouse.Uuid = ? AND Reservationhouse.Hid = ?;", rObjects);
                List<Object> pObjects = Arrays.asList(Constant.ID, item.getId());
                ResultSet pResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Pagree FROM House, Paydeposit WHERE Paydeposit.Uuid = ? AND Paydeposit.Hid = ?;", pObjects);
                List<Object> cObjects = Arrays.asList(Constant.ID, item.getId());
                ResultSet cResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Cagree FROM House, Completetransaction WHERE Completetransaction.Uuid = ? AND Completetransaction.Hid = ?;", cObjects);

                int rAgree = -1, pAgree = -1, cAgree = -1;
                try {
                    if (rResultSet != null && rResultSet.next())
                        rAgree = rResultSet.getInt(1);
                    if (pResultSet != null && pResultSet.next())
                        pAgree = pResultSet.getInt(1);
                    if (cResultSet != null && cResultSet.next())
                        cAgree = cResultSet.getInt(1);

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
        String sql = "INSERT INTO Paydeposit(Uuid, Hid, Pmoney, Pliquidated_money, Pagree) VALUES(?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(Constant.ID, house.getId(), 10000, 1000, 0);
        int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
        if (result > 0) {
            mPayDepositButton.setDisable(true);
            AlertUtil.alert("预约信息", "预约付定金成功");
            mPayDepositLabel.setVisible(true);
        } else {
            AlertUtil.alert("预约信息", "预约付定金失败");
        }
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        String sql = "INSERT INTO Completetransaction(Uuid, Hid, Csum_money, Cagree, Cintermediary_cost, Ctype) VALUES(?, ?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(Constant.ID, house.getId(), 10, 0, 1000, 1);
        int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
        if (result > 0) {
            mCompleteButton.setDisable(true);
            AlertUtil.alert("预约信息", "预约交付全款成功");
            mCompleteLabel.setVisible(true);
        } else {
            AlertUtil.alert("预约信息", "预约交付全款失败");
        }
    }

    public void mIntermediaryClicked(MouseEvent mouseEvent) {
        ResultSet resultSet = null;
        try {
            resultSet = JDBCHelper.getsInstance().executeQuery("SELECT Iname, Isex, Itel, Iemail FROM House, Intermediary WHERE House.Hid = ? AND House.Iid = Intermediary.Iid", Arrays.asList(house.getId()));
            if (resultSet.next()) {
                AlertUtil.alert("中介人员信息", "姓名：" + resultSet.getString(1) +
                        "\n\n性别：" + resultSet.getString(2) +
                        "\n\n联系方式：" + resultSet.getString(3) +
                        "\n\nEmail：" + resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFlag(int rAgree, int pAgree, int cAgree) {
        if (rAgree == 1) {
            image1.setImage(new Image(Constant.DO1));
            mLookHouseButton.setDisable(true);
            mLookHouseLabel.setVisible(false);
        } else if (rAgree == 0) {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(true);
            mLookHouseLabel.setVisible(true);
        } else {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(false);
            mLookHouseLabel.setVisible(false);
        }

        if (pAgree == 1) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
            mPayDepositButton.setDisable(true);
            mPayDepositLabel.setVisible(false);
        } else if (pAgree == 0) {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(true);
            mPayDepositLabel.setVisible(true);
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            if (rAgree == 1)
                mPayDepositButton.setDisable(false);
            else
                mPayDepositButton.setDisable(true);
            mPayDepositLabel.setVisible(false);
        }

        if (cAgree == 1) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
            mCompleteButton.setDisable(true);
            mCompleteLabel.setVisible(false);
        } else if (cAgree == 0) {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(true);
            mCompleteLabel.setVisible(true);
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            if (pAgree == 1)
                mCompleteButton.setDisable(false);
            else
                mCompleteButton.setDisable(true);
            mCompleteLabel.setVisible(false);
        }
    }

}
