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
import main.java.utils.ListViewHelper;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFlag(-1, -1, -1);

        listViewHelper = new ListViewHelper(mListView);
        listViewHelper.setListener("SELECT * FROM House WHERE Iid is null OR Iid = ?;", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                List<Object> rObjects = Arrays.asList(item.getId());
                ResultSet rResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Reservationhouse WHERE Reservationhouse.Hid = ? AND Reservationhouse.Hid = House.Hid;", rObjects);
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

    }

    public void mPayDepositButtonClicked(MouseEvent mouseEvent) {

    }

    public void mCompleteButtonClicked(MouseEvent mouseEvent) {

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
