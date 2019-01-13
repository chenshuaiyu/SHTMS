package main.java.listener;

import main.java.bean.House;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/12 9:57
 */
public interface UpdateHouseListener {
    void complete(House h);

    void cancel();
}
