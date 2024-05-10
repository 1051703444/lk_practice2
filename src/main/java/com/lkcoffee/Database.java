package com.lkcoffee;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description  数据库模拟类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10 15:13
 */
public class Database {
    private static Database database=new Database();
    // 使用Map模拟数据库表
    private Map<String, Order> ordersDB = new HashMap<>();
    private Map<String, Rider> ridersDB = new HashMap<>();

    public void addOrder(Order order) {
        ordersDB.put(order.getOrderId(), order);
    }

    public void addRider(Rider rider) {
        ridersDB.put(rider.getRiderId(), rider);
    }

    public Order getOrder(String orderId) {
        return ordersDB.get(orderId);
    }

    public Rider getRider(String riderId) {
        return ridersDB.get(riderId);
    }

    public static Database getInstance(){
        return database;
    }

}
