package de.supernerd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test
    void getAllOrdersWithOrderStatusTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");
        shopService.addOrder(productsIds);

        Order order1 = new Order("-1", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.PROCESSING);
        Order order2 = new Order("-2", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.COMPLETED);
        Order order3 = new Order("-3", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.COMPLETED);
        OrderRepo orderRepo = new OrderMapRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);

        shopService.setOrderRepo(orderRepo);

        List<Order> actual = shopService.getAllOrdersWithOrderStatus(OrderStatus.PROCESSING);

        Order expected = new Order("-2", List.of(new Product("2", "Birne")), OrderStatus.COMPLETED);
        assertEquals(1, actual.size());
        assertNotNull(expected.id());
    }

    @Test
    void getAllOrdersWithOrderStatusTwoOrdersTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");
        shopService.addOrder(productsIds);

        Order order1 = new Order("-1", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.PROCESSING);
        Order order2 = new Order("-2", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.PROCESSING);
        Order order3 = new Order("-3", List.of(new Product("1", "Apfel"), new Product("2", "Birne")), OrderStatus.COMPLETED);
        OrderRepo orderRepo = new OrderMapRepo();
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);

        shopService.setOrderRepo(orderRepo);

        List<Order> actual = shopService.getAllOrdersWithOrderStatus(OrderStatus.PROCESSING);

        Order expected = new Order("-2", List.of(new Product("2", "Birne")), OrderStatus.COMPLETED);
        assertEquals(2, actual.size());
        assertNotNull(expected.id());
    }

}
