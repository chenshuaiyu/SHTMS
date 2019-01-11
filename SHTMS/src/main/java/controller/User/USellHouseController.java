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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.bean.House;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class USellHouseController implements Initializable {

    @FXML
    public ListView mListView;
    public Button mAddButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<House> list = FXCollections.observableArrayList(
                new House(1, "山西", "太原", "尖草坪区", "文瀛", "1", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(2, "山西", "太原", "尖草坪区", "文瀛", "2", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(3, "山西", "太原", "尖草坪区", "文瀛", "3", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(4, "山西", "太原", "尖草坪区", "文瀛", "4", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(5, "山西", "太原", "尖草坪区", "文瀛", "5", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(6, "山西", "太原", "尖草坪区", "文瀛", "6", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(7, "山西", "太原", "尖草坪区", "文瀛", "7", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3),
                new House(8, "山西", "太原", "尖草坪区", "文瀛", "8", 3, 12, 100, 10, 2, 100, 1, 0, 1, 1, 3)
        );
        mListView.setItems(list);
        mListView.setCellFactory(e -> new ListCell<House>() {
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
                    Label l5 = new Label(item.getPropertyCost() + "万");
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
                            if (event.getClickCount() == 2)
                                System.out.println(item.getId());
                        }
                    });
                    setGraphic(hbox);
                }
            }
        });

    }


    public void mAddButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../resourses/fxml/AddHouse.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
