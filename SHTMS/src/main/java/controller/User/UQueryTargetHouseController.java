package main.java.controller.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Constant;
import main.java.db.JDBCHelper;
import main.java.listener.ListViewListener;
import main.java.listener.LookHouseListener;
import main.java.listener.QueryHouseListener;
import main.java.bean.House;
import main.java.controller.LookHouseController;
import main.java.controller.QueryHouseController;
import main.java.utils.AlertUtil;
import main.java.utils.ListViewHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;


public class UQueryTargetHouseController implements Initializable {
    @FXML
    private ListView mListView;
    @FXML
    private Button mQueryButton;
    private ListViewHelper listViewHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewHelper = new ListViewHelper(mListView);
        setHouse();

    }

    private void setHouse() {
        listViewHelper.setListener("SELECT * FROM House WHERE Uuid <> ?", Arrays.asList(Constant.ID), new ListViewListener() {
            @Override
            public void todo(House item) {
                System.out.println(item.getId());
                try {
                    Stage stage = new Stage();
                    stage.setAlwaysOnTop(true);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../../../resources/fxml/LookHouse.fxml"));
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
                    LookHouseController controller = loader.getController();
                    stage.setScene(new Scene(pane));
                    stage.show();

                    controller.setLookHouseListener(new LookHouseListener() {
                        @Override
                        public void lookHouse(House h) {
                            //数据库新增预约看房记录
                            String sql = "INSERT INTO Reservationhouse(Uuid, Hid, Ragree) VALUES(?, ?, ?)";
                            List<Object> params = Arrays.asList(Constant.ID, h.getId(), 0);
                            int result = JDBCHelper.getsInstance().executeUpdate(sql, params);
                            stage.close();
                            if (result > 0) {
                                AlertUtil.alert("预约信息", "预约看房成功");
                            } else {
                                AlertUtil.alert("预约信息", "预约看房失败");
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

    public void mQueryButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../../resources/fxml/QueryHouse.fxml"));
            AnchorPane pane = loader.load();
            QueryHouseController controller = loader.getController();
            stage.setScene(new Scene(pane));
            stage.show();

            controller.setQueryHouseListener(new QueryHouseListener() {
                @Override
                public void query(House h) {

                    stage.close();
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