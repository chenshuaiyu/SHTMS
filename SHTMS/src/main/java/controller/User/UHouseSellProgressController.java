package main.java.controller.User;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.listener.ListViewListener;
import main.java.bean.House;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UHouseSellProgressController implements Initializable {
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
    private ListView mListView;
    @FXML
    private Label mMoneyLabel;
    @FXML
    private Label mSunMoneyLabel;
    @FXML
    private Label mIntermediaryCostLabel;
    @FXML
    private Label mLiquidatedLabel;
    @FXML
    private Label mTypeLabel;

    private ObservableList<House> list = null;
    private ListViewHelper listViewHelper = null;
    private House house;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewHelper = new ListViewHelper(mListView);
        setHouse();
    }

    private void setHouse() {
        listViewHelper.setListener("SELECT * FROM House WHERE Uuid = ?", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                house = item;
                initLabel();

                List<Object> objects = Arrays.asList(Constant.ID, house.getId());
                ResultSet rResultSet = JDBCHelper.getInstance().executeQuery("SELECT Ragree FROM House, Reservationhouse WHERE House.Uuid = ? AND Reservationhouse.Hid = ? AND Reservationhouse.Hid = House.Hid;", objects);
                ResultSet pResultSet = JDBCHelper.getInstance().executeQuery("SELECT Pagree, Pmoney, Pliquidated_money FROM House, Paydeposit WHERE House.Uuid = ? AND Paydeposit.Hid = ? AND Paydeposit.Hid = House.Hid ;", objects);
                ResultSet cResultSet = JDBCHelper.getInstance().executeQuery("SELECT Cagree, Csum_money, Cintermediary_cost, Ctype FROM House, Completetransaction WHERE House.Uuid = ? AND Completetransaction.Hid = ? AND Completetransaction.Hid = House.Hid;", objects);

                int rAgree = -1, pAgree = -1, cAgree = -1;
                try {
                    if (rResultSet != null && rResultSet.next())
                        rAgree = rResultSet.getInt(1);
                    if (pResultSet != null && pResultSet.next()){
                        pAgree = pResultSet.getInt(1);
                        mMoneyLabel.setText(pResultSet.getInt(2) + "");
                        mLiquidatedLabel.setText(pResultSet.getInt(3) + "");
                    }
                    if (cResultSet != null && cResultSet.next()){
                        cAgree = cResultSet.getInt(1);
                        mSunMoneyLabel.setText(cResultSet.getFloat(2) + "");
                        mIntermediaryCostLabel.setText(cResultSet.getInt(3) + "");
                        mTypeLabel.setText(Constant.INTERMEDIARYCOSTTYPE.get(cResultSet.getInt(4)));
                    }

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



    public void mIntermediaryClicked(MouseEvent mouseEvent) {
        ResultSet resultSet = null;
        try {
            resultSet = JDBCHelper.getInstance().executeQuery("SELECT Iname, Isex, Itel, Iemail FROM House, Intermediary WHERE House.Hid = ? AND House.Iid = Intermediary.Iid", Arrays.asList(house.getId()));
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

    private void initLabel() {
        mMoneyLabel.setText(Constant.UNKNOWN);
        mSunMoneyLabel.setText(Constant.UNKNOWN);
        mIntermediaryCostLabel.setText(Constant.UNKNOWN);
        mLiquidatedLabel.setText(Constant.UNKNOWN);
        mTypeLabel.setText(Constant.UNKNOWN);
    }

    public void setFlag(int rAgree, int pAgree, int cAgree) {
        if (rAgree == 1) {
            image1.setImage(new Image(Constant.DO1));
        } else if (rAgree == 0) {
            image1.setImage(new Image(Constant.UNDO1));
        } else {
            image1.setImage(new Image(Constant.UNDO1));
        }

        if (pAgree == 1) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
        } else if (pAgree == 0) {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
        }

        if (cAgree == 1) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
        } else if (cAgree == 0) {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
        }
    }

}
