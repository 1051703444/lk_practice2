import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 订单类
@Data
@Builder
public class Order {
    private String orderId;
    private String store;
    private String customer;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
