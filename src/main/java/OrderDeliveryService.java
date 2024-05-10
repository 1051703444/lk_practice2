import java.time.LocalDateTime;

// 订单流转逻辑类
public class OrderDeliveryService {
    public Database database = new Database();

    public void simulateOrderDelivery(String orderId, String riderId) {
        Order order = database.getOrder(orderId);
        Rider rider = database.getRider(riderId);

        if (order == null || rider == null) {
            System.out.println("Order or Rider not found.");
            return;
        }

        if (!rider.isWorking()) {
            System.out.println("Rider is not working.");
            return;
        }

        if (order.getStatus() == OrderStatus.WAITING_FOR_PICKUP && rider.canTakeOrder()) {
            rider.takeOrder(order);
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

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
