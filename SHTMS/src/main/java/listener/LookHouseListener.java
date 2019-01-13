package main.java.listener;

import main.java.bean.House;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/12 9:58
 */
public interface LookHouseListener {
    void lookHouse(House h);

    void cancel();
}
