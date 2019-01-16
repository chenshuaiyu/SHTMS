package main.java.controller.intermediary;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class IManagerTransactionInformationController implements Initializable {
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

    private ListViewHelper listViewHelper;
    private House house;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewHelper = new ListViewHelper(mListView);
        setHouse();
    }

    private void setHouse() {
        listViewHelper.setListener("SELECT * FROM House", Arrays.asList(), new ListViewListener() {
            @Override
            public void todo(House item) {
                house = item;
                List<Object> objects = Arrays.asList(item.getId());
                ResultSet rResultSet = JDBCHelper.getInstance().executeQuery("SELECT Ragree FROM Reservationhouse WHERE Reservationhouse.Hid = ?;", objects);
                ResultSet pResultSet = JDBCHelper.getInstance().executeQuery("SELECT Pagree, Pmoney, Pliquidated_money FROM Paydeposit WHERE Paydeposit.Hid = ?;", objects);
                ResultSet cResultSet = JDBCHelper.getInstance().executeQuery("SELECT Cagree, Csum_money, Cintermediary_cost, Ctype FROM Completetransaction WHERE Completetransaction.Hid = ?;", objects);

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

    private void setFlag(int rAgree, int pAgree, int cAgree) {
        if (rAgree == 1) {
            image1.setImage(new Image(Constant.DO1));
        } else {
            image1.setImage(new Image(Constant.UNDO1));
        }

        if (pAgree == 1) {
            image2.setImage(new Image(Constant.DO2));
            image3.setImage(new Image(Constant.DO1));
        } else {
            image2.setImage(new Image(Constant.UNDO2));
            image3.setImage(new Image(Constant.UNDO1));
        }

        if (cAgree == 1) {
            image4.setImage(new Image(Constant.DO2));
            image5.setImage(new Image(Constant.DO1));
        } else {
            image4.setImage(new Image(Constant.UNDO2));
            image5.setImage(new Image(Constant.UNDO1));
        }

    }
}
