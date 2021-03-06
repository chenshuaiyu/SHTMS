package main.java.utils;

import javafx.scene.control.Alert;


public class AlertUtil {
    public static void alert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
