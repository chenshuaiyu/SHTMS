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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.listener.AddHouseListener;
import main.java.Constant;
import main.java.listener.ListViewListener;
import main.java.listener.UpdateHouseListener;
import main.java.bean.House;
import main.java.controller.AddHouseController;
import main.java.controller.UpdateHouseController;
import main.java.db.JDBCHelper;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelper;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
                    loader.setLocation(getClass().getResource("../../../resources/fxml/UpdateHouse.fxml"));
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
                            int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
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

//        String sql = "SELECT * FROM House WHERE Uuid = ?";
//        List<Object> objects = Arrays.asList(Constant.ID);
//        ResultSet resultSet = null;
//        try {
//            List<House> l = new ArrayList<>();
//            resultSet = JDBCHelper.getsInstance().executeQuery(sql, objects);
//            while (resultSet.next()) {
//                l.add(new House(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
//                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
//                        resultSet.getInt(9), resultSet.getFloat(10), Constant.ID, resultSet.getInt(12),
//                        resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16),
//                        0));
//            }
//            list = FXCollections.observableArrayList(l);
//            mListView.setItems(list);
//            mListView.setCellFactory(e -> new ListCell<House>() {
//                @Override
//                protected void updateItem(House item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (!empty && item != null) {
//                        Label l1 = new Label(item.getProvince() + item.getCity() + item.getCounty());
//                        l1.setPrefSize(120, 15);
//                        Label l2 = new Label(item.getCommunityName() + "小区");
//                        l2.setPrefSize(80, 15);
//                        Label l3 = new Label(item.getBuildingNo() + "号楼" + item.getFloor() + "层" + item.getNo() + "号");
//                        l3.setPrefSize(120, 15);
//                        Label l4 = new Label(item.getArea() + "㎡");
//                        l4.setPrefSize(65, 15);
//                        Label l5 = new Label(item.getPrice() + "万");
//                        l5.setPrefSize(65, 15);
//                        Label l6 = new Label(item.getRoomNum() + "");
//                        l6.setPrefSize(38, 15);
//                        Label l7 = new Label(item.getLivingRoomNum() + "");
//                        l7.setPrefSize(38, 15);
//
//                        HBox hbox = new HBox();
//                        hbox.setPadding(new Insets(0, 0, 0, 5));
//                        hbox.setSpacing(10);
//                        hbox.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7);
//                        hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                            @Override
//                            public void handle(MouseEvent event) {
//                                if (event.getClickCount() == 2) {
//                                    try {
//                                        Stage stage = new Stage();
//                                        stage.setTitle("修改房源信息");
//                                        stage.setAlwaysOnTop(true);
//                                        FXMLLoader loader = new FXMLLoader();
//                                        loader.setLocation(getClass().getResource("../../../resources/fxml/UpdateHouse.fxml"));
//                                        loader.setResources(new ResourceBundle() {
//                                            @Override
//                                            protected Object handleGetObject(String key) {
//                                                return item;
//                                            }
//                                            @Override
//                                            public Enumeration<String> getKeys() {
//                                                return new Enumeration<String>() {
//                                                    @Override
//                                                    public boolean hasMoreElements() {
//                                                        return true;
//                                                    }
//                                                    @Override
//                                                    public String nextElement() {
//                                                        return "House";
//                                                    }
//                                                };
//                                            }
//                                        });
//
//                                        AnchorPane pane = loader.load();
//                                        UpdateHouseController controller = loader.getController();
//                                        stage.setScene(new Scene(pane));
//                                        stage.show();
//
//                                        controller.setUpdateHouseListener(new UpdateHouseListener() {
//                                            @Override
//                                            public void complete(House h) {
//                                                String sql = "UPDATE House SET Hprovince = ?, Hcity = ?, Hcounty = ?, Hcommunity = ?," +
//                                                        "Hbuilding_no = ?, Hfloor = ?, Hno = ?, Harea = ?," +
//                                                        "Hprice = ?, Hproperty_cost = ?, His_decorated = ?, Hhas_garage = ?," +
//                                                        "Hroom_num = ?, Hliving_room_num = ? " +
//                                                        "WHERE Hid = ?";
//                                                List<Object> params = Arrays.asList(
//                                                        h.getProvince(), h.getCity(), h.getCounty(), h.getCommunityName(),
//                                                        h.getBuildingNo(), h.getFloor(), h.getNo(), h.getArea(),
//                                                        h.getPrice(), h.getPropertyCost(), h.getIsDecorated(), h.getHasGarage(),
//                                                        h.getRoomNum(), h.getLivingRoomNum(),
//                                                        h.getId());
//                                                int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
//                                                stage.close();
//                                                if (result > 0) {
//                                                    setHouse();
//                                                    System.out.println("修改成功");
//                                                    AlertUtil.alert("修改信息","修改成功");
//                                                } else {
//                                                    System.out.println("修改失败");
//                                                    AlertUtil.alert("修改信息","修改失败");
//                                                }
//                                            }
//
//                                            @Override
//                                            public void cancel() {
//                                                System.out.println("cancel");
//                                                stage.close();
//                                            }
//                                        });
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        });
//                        setGraphic(hbox);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public void mAddButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("新增房源信息");
            stage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../resources/fxml/AddHouse.fxml"));
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
                            "Hroom_num, Hliving_room_num, Uuid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    List<Object> objects = Arrays.asList(
                            h.getProvince(), h.getCity(), h.getCounty(), h.getCommunityName(),
                            h.getBuildingNo(), h.getFloor(), h.getNo(), h.getArea(),
                            h.getPrice(), h.getPropertyCost(), h.getIsDecorated(), h.getHasGarage(),
                            h.getRoomNum(), h.getLivingRoomNum(), h.getuId());
                    int result = JDBCHelper.getsInstance().executeUpdate(sql, objects);
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
