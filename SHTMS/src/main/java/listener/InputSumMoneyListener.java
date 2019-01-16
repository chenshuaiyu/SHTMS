package main.java.listener;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/15 9:21
 */
public interface InputSumMoneyListener {
    void confirm(float sumMoney, int intermediaryCost, int type);

    void cancel();
}
