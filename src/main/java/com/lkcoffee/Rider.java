package com.lkcoffee;
/**
 * @Description  骑手类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10 15:13
 */
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Optional;
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
