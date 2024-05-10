package com.lkcoffee;
/**
 * @Description 订单流转逻辑类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10
 */
import java.time.LocalDateTime;

public class OrderDeliveryService {
    public final static Database DATABASE = Database.getInstance();


    public void simulateOrderDelivery(String orderId, String riderId) {
        Order order = DATABASE.getOrder(orderId);
        Rider rider = DATABASE.getRider(riderId);

        if (order == null || rider == null) {
            System.out.println("Order or Rider not found.");
            return;
        }

        if (!rider.isWorking()) {
            System.out.println("Rider is not working.");
            return;
        }

        if(!rider.isHealthy()){
            System.out.println("Rider is not healthy.");
            return;
        }
        if (order.getRiderId()!=null&&order.getRiderId()!=riderId){
            System.out.println("The order has been accepted by someone else");
            return;
        }
        if (order.getStatus() == OrderStatus.WAITING_FOR_PICKUP && rider.canTakeOrder()) {
            rider.takeOrder(order);
            order.setRiderId(rider.getRiderId());
            System.out.println("Rider " + riderId + " took order " + orderId);
        } else if (order.getStatus() == OrderStatus.ON_THE_WAY) {
            // 检查是否超时
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(order.getCreatedAt().plusMinutes(30))) {
                order.setStatus(OrderStatus.EXPIRED);
                System.out.println("Order " + orderId + " has expired.");
            } else {
                rider.deliverOrder(orderId);
                System.out.println("Rider " + riderId + " delivered order " + orderId);
            }
        } else {
            System.out.println("Order is not in a valid status for delivery.");
        }
    }
}
