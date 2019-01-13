package main.java.controller.User;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.listener.ListViewListener;
import main.java.bean.House;
import main.java.db.JDBCHelper;
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

    private ObservableList<House> list = null;
    private ListViewHelper listViewHelper = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewHelper = new ListViewHelper(mListView);
        setHouse();
    }

    private void setHouse() {
        listViewHelper.setListener("SELECT * FROM House WHERE Uuid = ?", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                List<Object> rObjects = Arrays.asList(Constant.ID);
                ResultSet rResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Reservationhouse WHERE House.Uuid = ? AND Reservationhouse.Hid = House.Hid;", rObjects);
                List<Object> pObjects = Arrays.asList(Constant.ID);
                ResultSet pResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Paydeposit WHERE House.Uuid = ? AND Paydeposit.Hid = House.Hid ;", pObjects);
                List<Object> cObjects = Arrays.asList(Constant.ID);
                ResultSet cResultSet = JDBCHelper.getsInstance().executeQuery("SELECT * FROM House, Completetransaction WHERE Completetransaction.Uuid = ? AND Completetransaction.Hid = House.Hid;", cObjects);

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
