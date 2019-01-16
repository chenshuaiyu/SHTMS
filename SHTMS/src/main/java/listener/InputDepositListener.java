package main.java.listener;


public interface InputDepositListener {
    void confirm(int money, int liquidated);

    void cancel();
}
