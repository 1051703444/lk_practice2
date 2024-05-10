
/**
 * @Description 测试类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10 15:13
 */

import com.lkcoffee.*;
import org.testng.annotations.Test;
import java.util.ArrayList;

// 测试代码
public class PracticeTest {
    @Test
    public static void test() {
        OrderDeliveryService service = new OrderDeliveryService();
        OrderListQuery orderListQuery = new OrderListQuery();
        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .build();
        service.DATABASE.addOrder(order1);
        Order order2 = Order.builder()
                .orderId("002")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .build();
        service.DATABASE.addOrder(order2);

        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .orders(new ArrayList<>())
                .build();
        service.DATABASE.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();

        // 模拟订单配送
        service.simulateOrderDelivery("001", "R001");
        service.simulateOrderDelivery("002", "R001");
        orderListQuery.getListQuery("R001");
    }


}