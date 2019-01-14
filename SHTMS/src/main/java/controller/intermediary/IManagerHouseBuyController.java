package main.java.controller.intermediary;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.bean.House;
import main.java.db.JDBCHelper;
import main.java.listener.ListViewListener;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class IManagerHouseBuyController implements Initializable {
    @FXML
    private ListView mListView;
    @FXML
    private Button mLookHouseButton;
    @FXML
    private Button mPayDepositButton;
    @FXML
    private Button mCompleteButton;
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

    private ListViewHelper listViewHelper;

    private House house;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFlag(-1, -1, -1);

        listViewHelper = new ListViewHelper(mListView);
        listViewHelper.setListener("SELECT * FROM House WHERE Iid is null OR Iid = ?;", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                house = item;
                List<Object> rObjects = Arrays.asList(item.getId());
                ResultSet rResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Ragree FROM Reservationhouse WHERE Reservationhouse.Hid = ?;", rObjects);
                List<Object> pObjects = Arrays.asList(item.getId());
                ResultSet pResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Pagree FROM Paydeposit WHERE Paydeposit.Hid = ?;", pObjects);
                List<Object> cObjects = Arrays.asList(item.getId());
                ResultSet cResultSet = JDBCHelper.getsInstance().executeQuery("SELECT Cagree FROM Completetransaction WHERE Completetransaction.Hid = ?;", cObjects);

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
        List<Object> objects = Arrays.asList(house.getId());
        int result = JDBCHelper.getsInstance().executeUpdate("UPDATE Reservationhouse SET Ragree = 1 WHERE Reservationhouse.Hid = ?;", objects);
        if (result > 0) {
            image1.setImage(new Image(Constant.DO1));
            mLookHouseButton.setDisable(true);
            JDBCHelper.getsInstance().executeUpdate("Update House SET Iid = ? WHERE Hid = ?", Arrays.asList(Constant.ID, house.getId()));
            AlertUtil.alert("预约看房", "已同意预约看房");
        } else {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(false);
            AlertUtil.alert("预约看房", "同意预约看房失败");
        }
    }

    public void mPayDepositButtonClicked(MouseEvent mouseEvent) {
        List<Object> objects = Arrays.asList(house.getId());
        int result = JDBCHelper.getsInstance().executeUpdate("UPDATE Paydeposit SET Pagree = 1 WHERE Paydeposit.Hid = ?;", objects);
        if (result > 0) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
            mPayDepositButton.setDisable(true);
            AlertUtil.alert("交付定金", "已同意交付定金");
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(false);
            AlertUtil.alert("交付定金", "同意交付定金失败");
        }
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        List<Object> objects = Arrays.asList(house.getId());
        int result = JDBCHelper.getsInstance().executeUpdate("UPDATE Completetransaction SET Cagree = 1 WHERE Completetransaction.Hid = ?;", objects);
        if (result > 0) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
            mCompleteButton.setDisable(true);
            AlertUtil.alert("完成交易", "已同意完成交易");
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(false);
            AlertUtil.alert("完成交易", "同意完成交易失败");
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
        } else if (rAgree == 0) {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(false);
        } else {
            image1.setImage(new Image(Constant.UNDO1));
            mLookHouseButton.setDisable(true);
        }

        if (pAgree == 1) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
            mPayDepositButton.setDisable(true);
        } else if (pAgree == 0) {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(false);
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
            mPayDepositButton.setDisable(true);
        }

        if (cAgree == 1) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
            mCompleteButton.setDisable(true);
        } else if (cAgree == 0) {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(false);
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
            mCompleteButton.setDisable(true);
        }

    }


}
