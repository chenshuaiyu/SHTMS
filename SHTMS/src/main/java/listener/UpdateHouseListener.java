package main.java.listener;

import main.java.bean.House;


public interface UpdateHouseListener {
    void complete(House h);

    void cancel();
}
