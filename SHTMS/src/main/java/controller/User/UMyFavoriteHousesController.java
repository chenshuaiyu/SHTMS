package main.java.controller.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Constant;
import main.java.bean.House;
import main.java.controller.User.children.InputDepositController;
import main.java.controller.User.children.InputSumMoneyController;
import main.java.db.JDBCHelper;
import main.java.listener.InputDepositListener;
import main.java.listener.InputSumMoneyListener;
import main.java.listener.ListViewListenerForUser;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelperForUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
    public Label mStatusLabel;

    private ListViewHelperForUser listViewHelperForUser;

    private House house;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mLookHouseButton.setDisable(true);
        mPayDepositButton.setDisable(true);
        mCompleteButton.setDisable(true);

        listViewHelperForUser = new ListViewHelperForUser(mListView);
        listViewHelperForUser.setListener("SELECT * FROM House, Reservationhouse WHERE Reservationhouse.Uuid = ? AND House.Hid = Reservationhouse.Hid;", Arrays.asList(Constant.ID), new ListViewListenerForUser() {
            @Override
            public void todo(House item) {
                house = item;

                initLabel();
                List<Object> objects = Arrays.asList(Constant.ID, item.getId());
                ResultSet rResultSet = JDBCHelper.getInstance().executeQuery("SELECT Ragree FROM House, Reservationhouse WHERE Reservationhouse.Uuid = ? AND Reservationhouse.Hid = ?;", objects);
                ResultSet pResultSet = JDBCHelper.getInstance().executeQuery("SELECT Pagree, Pmoney, Pliquidated_money FROM House, Paydeposit WHERE Paydeposit.Uuid = ? AND Paydeposit.Hid = ?;", objects);
                ResultSet cResultSet = JDBCHelper.getInstance().executeQuery("SELECT Cagree, Csum_money, Cintermediary_cost, Ctype FROM House, Completetransaction WHERE Completetransaction.Uuid = ? AND Completetransaction.Hid = ?;", objects);

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
                    }

                    System.out.println(rAgree);
                    System.out.println(pAgree);
                    System.out.println(cAgree);

                    setFlag(rAgree, pAgree, cAgree);

                    ResultSet resultSet = JDBCHelper.getInstance().executeQuery("SELECT Hstatus FROM House WHERE Hid = ?", Arrays.asList(house.getId()));
                    resultSet.next();
                    int status = resultSet.getInt(1);
                    if (status == 1) {
                        mLookHouseButton.setDisable(true);
                        mPayDepositButton.setDisable(true);
                        mCompleteButton.setDisable(true);
                        mStatusLabel.setText(Constant.SOLD);
                        mLookHouseLabel.setVisible(false);
                        mPayDepositLabel.setVisible(false);
                        mCompleteLabel.setVisible(false);
                    } else {
                        mStatusLabel.setText(Constant.UNSOLD);
                    }

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
        try {
            Stage stage = new Stage();
            stage.setTitle("填写定金信息");
            stage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../resources/fxml/user/children/InputDeposit.fxml"));
            AnchorPane pane = loader.load();
            InputDepositController controller = loader.getController();
            stage.setScene(new Scene(pane));
            stage.show();

            controller.setListener(new InputDepositListener() {
                @Override
                public void confirm(int money, int liquidated) {
                    String sql = "INSERT INTO Paydeposit(Uuid, Hid, Pdate, Pmoney, Pliquidated_money, Pagree) VALUES(?, ?, ?, ?, ?, ?)";
                    List<Object> params = Arrays.asList(Constant.ID, house.getId(),new Date(System.currentTimeMillis()), money, liquidated, 0);
                    int result = JDBCHelper.getInstance().executeUpdate(sql, params);
                    stage.close();
                    if (result > 0) {
                        mPayDepositButton.setDisable(true);
                        AlertUtil.alert("预约信息", "预约付定金成功");
                        mPayDepositLabel.setVisible(true);
                    } else {
                        AlertUtil.alert("预约信息", "预约付定金失败");
                    }
                }

                @Override
                public void cancel() {
                    stage.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("填写全款信息");
            stage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../resources/fxml/user/children/InputSumMoney.fxml"));
            AnchorPane pane = loader.load();
            InputSumMoneyController controller = loader.getController();
            stage.setScene(new Scene(pane));
            stage.show();

            controller.setListener(new InputSumMoneyListener() {
                @Override
                public void confirm(float sumMoney, int intermediaryCost, int type) {
                    String sql = "INSERT INTO Completetransaction(Uuid, Hid, Cdate, Csum_money, Cagree, Cintermediary_cost, Ctype) VALUES(?, ?, ?, ?, ?, ?, ?)";
                    List<Object> params = Arrays.asList(Constant.ID, house.getId(), new Date(System.currentTimeMillis()), sumMoney, 0, intermediaryCost, type);
                    int result = JDBCHelper.getInstance().executeUpdate(sql, params);
                    stage.close();
                    if (result > 0) {
                        mCompleteButton.setDisable(true);
                        AlertUtil.alert("预约信息", "预约交付全款成功");
                        mCompleteLabel.setVisible(true);
                    } else {
                        AlertUtil.alert("预约信息", "预约交付全款失败");
                    }
                }

                @Override
                public void cancel() {
                    stage.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mIntermediaryClicked(MouseEvent mouseEvent) {
        if (house != null) {
            ResultSet resultSet = null;
            try {
                resultSet = JDBCHelper.getInstance().executeQuery("SELECT Iname, Isex, Itel, Iemail FROM House, Intermediary WHERE House.Hid = ? AND House.Iid is not null AND House.Iid = Intermediary.Iid", Arrays.asList(house.getId()));
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
