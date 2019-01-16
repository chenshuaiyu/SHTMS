package main.java.controller.intermediary;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.bean.HouseRecord;
import main.java.db.JDBCHelper;
import main.java.listener.ListViewListenerForIntermediary;
import main.java.utils.AlertUtil;
import main.java.utils.DateUtil;
import main.java.utils.ListViewHelperForIntermediary;
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
    @FXML
    private Label mDateLabel;

    private ListViewHelperForIntermediary listViewHelperForIntermediary;
    private HouseRecord record;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewHelperForIntermediary = new ListViewHelperForIntermediary(mListView);
        setHouse();
    }

    private void setHouse() {
        listViewHelperForIntermediary.setListener("SELECT * FROM House, Completetransaction WHERE House.Hid = Completetransaction.Hid AND Completetransaction.Cagree = 1;", Arrays.asList(), new ListViewListenerForIntermediary() {
            @Override
            public void todo(HouseRecord item) {
                record = item;

                List<Object> objects = Arrays.asList(record.getId(), record.getBuyerId());
                ResultSet rResultSet = JDBCHelper.getInstance().executeQuery("SELECT Ragree FROM Reservationhouse WHERE Reservationhouse.Hid = ? AND Reservationhouse.Uuid = ?;", objects);
                ResultSet pResultSet = JDBCHelper.getInstance().executeQuery("SELECT Pagree, Pmoney, Pliquidated_money FROM Paydeposit WHERE Paydeposit.Hid = ? AND Paydeposit.Uuid = ?;", objects);
                ResultSet cResultSet = JDBCHelper.getInstance().executeQuery("SELECT Cagree, Csum_money, Cintermediary_cost, Ctype, Cdate FROM Completetransaction WHERE Completetransaction.Hid = ? AND Completetransaction.Uuid = ?;", objects);

                int rAgree = -1, pAgree = -1, cAgree = -1;
                try {
                    if (rResultSet != null && rResultSet.next())
                        rAgree = rResultSet.getInt(1);
                    if (pResultSet != null && pResultSet.next()) {
                        pAgree = pResultSet.getInt(1);
                        mMoneyLabel.setText(pResultSet.getInt(2) + "");
                        mLiquidatedLabel.setText(pResultSet.getInt(3) + "");
                    }
                    if (cResultSet != null && cResultSet.next()) {
                        cAgree = cResultSet.getInt(1);
                        mSunMoneyLabel.setText(cResultSet.getFloat(2) + "");
                        mIntermediaryCostLabel.setText(cResultSet.getInt(3) + "");
                        mTypeLabel.setText(Constant.INTERMEDIARYCOSTTYPE.get(cResultSet.getInt(4)));
                        mDateLabel.setText(DateUtil.transform(cResultSet.getDate(5)));
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

    public void mBuyerClicked(MouseEvent mouseEvent) {
        if (record != null) {
            ResultSet resultSet = null;
            try {
                resultSet = JDBCHelper.getInstance().executeQuery("SELECT Uname, Usex, Utel, Uemail FROM Uuser WHERE Uuid = ?;", Arrays.asList(record.getBuyerId()));
                if (resultSet.next()) {
                    AlertUtil.alert("买方信息", "姓名：" + resultSet.getString(1) +
                            "\n\n性别：" + resultSet.getString(2) +
                            "\n\n联系方式：" + resultSet.getString(3) +
                            "\n\nEmail：" + resultSet.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
