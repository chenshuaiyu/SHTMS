package main.java.controller.User;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.listener.AddHouseListener;
import main.java.Constant;
import main.java.listener.ListViewListener;
import main.java.listener.UpdateHouseListener;
import main.java.bean.House;
import main.java.controller.User.children.AddHouseController;
import main.java.controller.User.children.UpdateHouseController;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelper;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class USellHouseManagerController implements Initializable {
    @FXML
    private ListView mListView;
    @FXML
    private Button mAddButton;

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
                try {
                    Stage stage = new Stage();
                    stage.setTitle("修改房源信息");
                    stage.setAlwaysOnTop(true);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../../../resources/fxml/user/children/UpdateHouse.fxml"));
                    loader.setResources(new ResourceBundle() {
                        @Override
                        protected Object handleGetObject(String key) {
                            return item;
                        }

                        @Override
                        public Enumeration<String> getKeys() {
                            return new Enumeration<String>() {
                                @Override
                                public boolean hasMoreElements() {
                                    return true;
                                }

                                @Override
                                public String nextElement() {
                                    return "House";
                                }
                            };
                        }
                    });

                    AnchorPane pane = loader.load();
                    UpdateHouseController controller = loader.getController();
                    stage.setScene(new Scene(pane));
                    stage.show();

                    controller.setUpdateHouseListener(new UpdateHouseListener() {
                        @Override
                        public void complete(House h) {
                            String sql = "UPDATE House SET Hprovince = ?, Hcity = ?, Hcounty = ?, Hcommunity = ?," +
                                    "Hbuilding_no = ?, Hfloor = ?, Hno = ?, Harea = ?," +
                                    "Hprice = ?, Hproperty_cost = ?, His_decorated = ?, Hhas_garage = ?," +
                                    "Hroom_num = ?, Hliving_room_num = ? " +
                                    "WHERE Hid = ?";
                            List<Object> params = Arrays.asList(
                                    h.getProvince(), h.getCity(), h.getCounty(), h.getCommunityName(),
                                    h.getBuildingNo(), h.getFloor(), h.getNo(), h.getArea(),
                                    h.getPrice(), h.getPropertyCost(), h.getIsDecorated(), h.getHasGarage(),
                                    h.getRoomNum(), h.getLivingRoomNum(),
                                    h.getId());
                            int result = JDBCHelper.getInstance().executeUpdate(sql, params);
                            stage.close();
                            if (result > 0) {
                                setHouse();
                                System.out.println("修改成功");
                                AlertUtil.alert("修改信息", "修改成功");
                            } else {
                                System.out.println("修改失败");
                                AlertUtil.alert("修改信息", "修改失败");
                            }
                        }

                        @Override
                        public void cancel() {
                            System.out.println("cancel");
                            stage.close();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void mAddButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("新增房源信息");
            stage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../resources/fxml/user/children/AddHouse.fxml"));
            AnchorPane pane = loader.load();
            AddHouseController controller = loader.getController();
            stage.setScene(new Scene(pane));
            stage.show();

            controller.setAddHouseListener(new AddHouseListener() {
                @Override
                public void add(House h) {
                    String sql = "INSERT INTO House(" +
                            "Hprovince, Hcity, Hcounty, Hcommunity," +
                            "Hbuilding_no, Hfloor, Hno, Harea," +
                            "Hprice, Hproperty_cost, His_decorated, Hhas_garage," +
                            "Hroom_num, Hliving_room_num, Uuid, Hstatus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    List<Object> objects = Arrays.asList(
                            h.getProvince(), h.getCity(), h.getCounty(), h.getCommunityName(),
                            h.getBuildingNo(), h.getFloor(), h.getNo(), h.getArea(),
                            h.getPrice(), h.getPropertyCost(), h.getIsDecorated(), h.getHasGarage(),
                            h.getRoomNum(), h.getLivingRoomNum(), h.getuId(), 0);
                    int result = JDBCHelper.getInstance().executeUpdate(sql, objects);
                    stage.close();
                    if (result > 0) {
                        setHouse();
                        System.out.println("新增成功");
                        AlertUtil.alert("新增信息", "新增成功");
                    } else {
                        System.out.println("新增失败");
                        AlertUtil.alert("新增信息", "新增失败");
                    }
                }

                @Override
                public void cancel() {
                    System.out.println("cancel");
                    stage.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
