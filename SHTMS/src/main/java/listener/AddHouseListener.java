package main.java.listener;


import main.java.bean.House;

public interface AddHouseListener {
    void add(House h);

    void cancel();
}
