package main.java.controller.User.children;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import main.java.listener.InputDepositListener;


public class InputDepositController {
    @FXML
    private JFXTextField mMoneyTextField;
    @FXML
    private JFXTextField mLiquidatedTextField;
    @FXML
    private Button mConfirmButton;
    @FXML
    private Button mCancelButton;

    private InputDepositListener listener;

    public void setListener(InputDepositListener l){
        listener = l;
    }

    public void mConfirmButtonClicked(MouseEvent mouseEvent) {
        int money = Integer.parseInt(mMoneyTextField.getText());
        int liquidated = Integer.parseInt(mLiquidatedTextField.getText());
        listener.confirm(money, liquidated);
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }
}
