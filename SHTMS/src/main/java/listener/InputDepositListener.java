package main.java.listener;

/**
 * Coder : chenshuaiyu
 * Time : 2019/1/15 9:00
 */
public interface InputDepositListener {
    void confirm(int money, int liquidated);

    void cancel();
}
