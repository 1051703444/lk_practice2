import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// 订单状态枚举
enum OrderStatus {
    WAITING_FOR_PICKUP, // 待接单
    ON_THE_WAY,         // 配送中
    DELIVERED,          // 配送完成
    EXPIRED            // 超时
}

// 订单类
class Order {
    private String orderId;
    private String store;
    private String customer;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order(String orderId, String store, String customer) {
        this.orderId = orderId;
        this.store = store;
        this.customer = customer;
        this.status = OrderStatus.WAITING_FOR_PICKUP;
        this.createdAt = LocalDateTime.now();
    }

    // Getter 和 Setter 方法

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

// 骑手类
class Rider {
    private String riderId;
    private boolean isWorking;
    private List<Order> orders;

    public Rider(String riderId) {
        this.riderId = riderId;
        this.isWorking = false;
        this.orders = new ArrayList<>();
    }

    public void startWork() {
        isWorking = true;
    }

    public void endWork() {
        isWorking = false;
    }

    public boolean canTakeOrder() {
        return isWorking && orders.size() < 3;
    }

    public void takeOrder(Order order) {
        if (canTakeOrder()) {
            orders.add(order);
            order.setStatus(OrderStatus.ON_THE_WAY);
        }
    }

    public void deliverOrder(String orderId) {
        Optional<Order> order = orders.stream().filter(o -> o.getOrderId().equals(orderId)).findFirst();
        order.ifPresent(o -> {
            o.setStatus(OrderStatus.DELIVERED);
            orders.remove(o);
        });
    }

    // Getter 和 Setter 方法

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

// 数据库模拟类
class Database {
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

    // 其他数据库操作模拟方法
    // ...
}

// 测试代码
public class Main {
    public static void main(String[] args) {
        OrderDeliveryService service = new OrderDeliveryService();

        // 添加订单到数据库
        Order order1 = new Order("001", "Starbucks", "John Doe");
        service.database.addOrder(order1);

        // 添加骑手到数据库
        Rider rider1 = new Rider("R001");
        service.database.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();

        // 模拟订单配送
        service.simulateOrderDelivery("001", "R001");
    }
}