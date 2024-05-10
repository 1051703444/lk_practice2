
/**
 * @Description 测试类
 * @author 陈志雄
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2024/5/10
 */

import com.lkcoffee.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class PracticeTest {
    static Database database = Database.getInstance();

    @BeforeTest
    public void beforeTest(){
        Database instance = Database.getInstance();
        instance.clearMaps();
    }

    @AfterTest
    public void afterTest(){
        Database instance = Database.getInstance();
        instance.clearMaps();
    }
    @Test
    public static void testOrderTake() {
        OrderDeliveryService service = new OrderDeliveryService();

        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .build();
        database.addOrder(order1);
        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .orders(new ArrayList<>())
                .isHealthy(true)
                .build();
        database.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();

        // 模拟订单配送
        service.simulateOrderDelivery("001", "R001");
    }

    @Test
    public void testOrderQuery(){
        OrderListQuery orderListQuery = new OrderListQuery();
        OrderDeliveryService service = new OrderDeliveryService();
        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .createdAt(LocalDateTime.now())
                .build();
        database.addOrder(order1);

        // 添加订单到数据库
        Order order2 = Order.builder()
                .orderId("002")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .createdAt(LocalDateTime.now())
                .build();
        database.addOrder(order2);

        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .orders(new ArrayList<>())
                .isHealthy(true)
                .build();
        database.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();
        // 模拟订单配送
        service.simulateOrderDelivery(order1.getOrderId(), rider1.getRiderId());
        service.simulateOrderDelivery(order2.getOrderId(), rider1.getRiderId());
        orderListQuery.getListQuery(rider1.getRiderId());
    }

    @Test
    public void testRiderIsHealthy(){
        OrderListQuery orderListQuery = new OrderListQuery();
        OrderDeliveryService service = new OrderDeliveryService();
        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .createdAt(LocalDateTime.now())
                .build();
        database.addOrder(order1);

        // 添加订单到数据库
        Order order2 = Order.builder()
                .orderId("002")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .createdAt(LocalDateTime.now())
                .build();
        database.addOrder(order2);

        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .orders(new ArrayList<>())
                .isHealthy(false)
                .build();
        database.addRider(rider1);

        // 骑手开始工作
        rider1.startWork();
        // 模拟订单配送
        service.simulateOrderDelivery(order1.getOrderId(), rider1.getRiderId());
        service.simulateOrderDelivery(order2.getOrderId(), rider1.getRiderId());
        orderListQuery.getListQuery(rider1.getRiderId());
    }
    @Test
    public void testOrderByOtherTake(){
        OrderListQuery orderListQuery = new OrderListQuery();
        OrderDeliveryService service = new OrderDeliveryService();
        // 添加订单到数据库
        Order order1 = Order.builder()
                .orderId("001")
                .store("lkcoffe")
                .customer("chen zhixiong")
                .status(OrderStatus.WAITING_FOR_PICKUP)
                .createdAt(LocalDateTime.now())
                .build();
        database.addOrder(order1);

        // 添加骑手到数据库
        Rider rider1 = Rider.builder()
                .riderId("R001")
                .orders(new ArrayList<>())
                .isHealthy(true)
                .build();
        database.addRider(rider1);
        Rider rider2 = Rider.builder()
                .riderId("R002")
                .orders(new ArrayList<>())
                .isHealthy(true)
                .build();
        database.addRider(rider2);

        // 骑手开始工作
        rider1.startWork();
        rider2.startWork();
        // 模拟订单配送
        service.simulateOrderDelivery(order1.getOrderId(), rider1.getRiderId());
        service.simulateOrderDelivery(order1.getOrderId(), rider2.getRiderId());
    }

}
