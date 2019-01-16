package main.java.listener;


import main.java.bean.House;

import java.util.List;

public interface QueryHouseListener {
    void query(String sql);

    void cancel();
}
