package main.java.controller.User;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import main.java.bean.House;

import java.net.URL;
import java.util.ResourceBundle;

public class USellHouseController implements Initializable {

    @FXML
    public TableView mTableView;
    private final ObservableList<House> data =
            FXCollections.observableArrayList(

            );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mTableView.setItems(data);
    }
}
