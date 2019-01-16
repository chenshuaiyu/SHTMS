package main.java.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main.java.bean.House;
import main.java.db.JDBCHelper;
import main.java.listener.ListViewListenerForUser;
import java.sql.ResultSet;
import java.util.*;


public class ListViewHelperForUser {
    private ListView listView;
    private ListViewListenerForUser listener;
    private ObservableList<House> list;

    public ListViewHelperForUser(ListView listView) {
        this.listView = listView;
    }

    public void setListener(String sql, List<Object> params, ListViewListenerForUser listener) {
        this.listener = listener;

        ResultSet resultSet = null;
        try {
            List<House> l = new ArrayList<>();
            resultSet = JDBCHelper.getInstance().executeQuery(sql, params);
            while (resultSet.next()) {
                l.add(new House(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8),
                        resultSet.getInt(9), resultSet.getFloat(10), resultSet.getInt(11), resultSet.getInt(12),
                        resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16),
                        0));
            }
            list = FXCollections.observableArrayList(l);
            listView.setItems(list);
            listView.setCellFactory(e -> new ListCell<House>() {
                @Override
                protected void updateItem(House item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        Label l1 = new Label(item.getProvince() + item.getCity() + item.getCounty());
                        l1.setPrefSize(120, 15);
                        Label l2 = new Label(item.getCommunityName() + "小区");
                        l2.setPrefSize(80, 15);
                        Label l3 = new Label(item.getBuildingNo() + "号楼" + item.getFloor() + "层" + item.getNo() + "号");
                        l3.setPrefSize(120, 15);
                        Label l4 = new Label(item.getArea() + "㎡");
                        l4.setPrefSize(65, 15);
                        Label l5 = new Label(item.getPrice() + "万");
                        l5.setPrefSize(65, 15);
                        Label l6 = new Label(item.getRoomNum() + "");
                        l6.setPrefSize(38, 15);
                        Label l7 = new Label(item.getLivingRoomNum() + "");
                        l7.setPrefSize(38, 15);

                        HBox hbox = new HBox();
                        hbox.setPadding(new Insets(0, 0, 0, 5));
                        hbox.setSpacing(10);
                        hbox.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7);
                        hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getClickCount() == 2) {
                                    listener.todo(item);
                                }
                            }
                        });
                        setGraphic(hbox);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
