package com.lkcoffee;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * @Description  订单类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10
 */
@Data
@Builder
public class Order {
    private String orderId;
    private String store;
    private String customer;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private String riderId;
}
