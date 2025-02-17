package de.supernerd;

import de.supernerd.exceptions.ProductNotFoundException;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new ProductNotFoundException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getAllOrdersWithOrderStatus(OrderStatus orderStatus) {

        return orderRepo.getOrders().stream().filter(n -> n.orderStatus() == orderStatus).toList();
    }

    public Order updateOrder(String id, OrderStatus orderStatus) {
        Order orderOriginal = orderRepo.getOrderById(id);
        Order orderCopy = orderOriginal.withOrderStatus(orderStatus);
        System.out.println(orderCopy);

        return orderCopy;
    }
}
