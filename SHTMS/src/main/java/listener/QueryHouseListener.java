package main.java.listener;



public interface QueryHouseListener {
    void query(String sql);

    void cancel();
}
