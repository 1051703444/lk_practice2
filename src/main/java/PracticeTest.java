import lombok.Builder;
import lombok.Data;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// 测试代码
public class PracticeTest {
    @Test
    public static void test() {
        OrderDeliveryService service = new OrderDeliveryService();

        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong").
                build();
        service.database.addOrder(order1);

        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .build();
        service.database.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();

        // 模拟订单配送
        service.simulateOrderDelivery("001", "R001");
    }
}