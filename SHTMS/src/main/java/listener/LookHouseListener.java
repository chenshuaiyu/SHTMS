package main.java.listener;

import main.java.bean.House;

public interface LookHouseListener {
    void lookHouse(House h);

    void cancel();
}
