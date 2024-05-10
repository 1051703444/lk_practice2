import java.util.HashMap;
import java.util.Map;

// 数据库模拟类
public class Database {
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

}
