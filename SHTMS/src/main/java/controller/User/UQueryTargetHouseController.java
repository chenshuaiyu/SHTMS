package main.java.controller.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.QueryHouseListener;
import main.java.controller.QueryHouseController;

import java.io.IOException;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/10 20:33
 */
public class UQueryTargetHouseController {
    @FXML
    private ListView mListView;
    @FXML
    private Button mQueryButton;

    public void mQueryButtonClicked(MouseEvent mouseEvent) {
        try {
            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../resourses/fxml/QueryHouse.fxml"))));
            stage.show();

            QueryHouseController.setQueryHouse(new QueryHouseListener() {
                @Override
                public void query(String s) {
                    System.out.println(s);
                    System.out.println("query");
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
