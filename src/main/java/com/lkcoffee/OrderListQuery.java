package com.lkcoffee;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author 陈志雄
 * @version V1.0
 * @Description
 * @Package com.lkcoffee
 * @date 2024/5/10
 */
public class OrderListQuery {
    public  final static Database DATABASE = Database.getInstance();

    public void getListQuery(String riderId){
        Rider rider = DATABASE.getRider(riderId);

        List<Order> orders = rider.getOrders();
        orders.forEach(order -> System.out.println(order));
    }
}
