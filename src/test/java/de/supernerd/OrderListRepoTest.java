package de.supernerd;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getOrders() {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, zonedDateTime);
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), OrderStatus.PROCESSING, zonedDateTime));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, zonedDateTime);
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, zonedDateTime);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, zonedDateTime);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, zonedDateTime);
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
