package main.java.controller.User;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/10 20:34
 */
public class UHouseSellProgressController {
    public ImageView p1;
    public ImageView p2;
    public ImageView p3;
    public ImageView p4;
    public ImageView p5;


    public void p11(MouseEvent mouseEvent) {
        p1.setImage(new Image("file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\java\\\\controller\\\\User\\\\do.png"));

    }
    public void p31(MouseEvent mouseEvent) {
        p2.setImage(new Image("file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\java\\\\controller\\\\User\\\\do11.png"));
        p3.setImage(new Image("file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\java\\\\controller\\\\User\\\\do.png"));
    }
    public void p51(MouseEvent mouseEvent) {
        p4.setImage(new Image("file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\java\\\\controller\\\\User\\\\do11.png"));
        p5.setImage(new Image("file:E:\\\\Java\\\\IntelliJ IDEA\\\\IJ\\\\SHTMSDemo\\\\src\\\\main\\\\java\\\\controller\\\\User\\\\do.png"));
    }

}
