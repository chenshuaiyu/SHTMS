package main.java.listener;


import main.java.bean.House;

public interface QueryHouseListener {
    void query(House h);

    void cancel();
}
