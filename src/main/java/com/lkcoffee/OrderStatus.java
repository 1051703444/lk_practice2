package com.lkcoffee;

/**
 * @Description  订单状态枚举变量
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10 15:13
 */
public enum OrderStatus {
    WAITING_FOR_PICKUP, // 待接单
    ON_THE_WAY,         // 配送中
    DELIVERED,          // 配送完成
    EXPIRED            // 超时
}
