import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

// 骑手类
@Data
@Builder
public class Rider {
    private String riderId;
    private boolean isWorking;
    private List<Order> orders;

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
}
