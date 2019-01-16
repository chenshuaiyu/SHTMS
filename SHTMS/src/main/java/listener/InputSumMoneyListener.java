package main.java.listener;


public interface InputSumMoneyListener {
    void confirm(float sumMoney, int intermediaryCost, int type);

    void cancel();
}
