package main.java.controller.User.children;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import main.java.Constant;
import main.java.listener.InputSumMoneyListener;
import java.net.URL;
import java.util.ResourceBundle;


public class InputSumMoneyController implements Initializable {
    @FXML
    private JFXTextField mSumMoneyTextField;
    @FXML
    private JFXTextField mIntermediaryCostTextField;
    @FXML
    private ChoiceBox mTypeChoiceBox;
    @FXML
    private Button mConfirmButton;
    @FXML
    private Button mCancelButton;

    private InputSumMoneyListener listener;

    public void setListener(InputSumMoneyListener l){
        listener = l;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mTypeChoiceBox.setItems(Constant.INTERMEDIARYCOSTTYPE);
    }

    public void mConfirmButtonClicked(MouseEvent mouseEvent) {
        float sumMoney = Float.parseFloat(mSumMoneyTextField.getText());
        System.out.println(sumMoney);
        int intermediaryCost = Integer.parseInt(mIntermediaryCostTextField.getText());
        int type = mTypeChoiceBox.getSelectionModel().getSelectedIndex();
        listener.confirm(sumMoney, intermediaryCost, type);
    }

    public void mCancelButtonClicked(MouseEvent mouseEvent) {
        listener.cancel();
    }
}
