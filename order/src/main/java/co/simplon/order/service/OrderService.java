package co.simplon.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.order.client.InventoryClient;
import co.simplon.order.entity.Order;
import co.simplon.order.repository.OrderRepository;

@Service
public class OrderService {
    private InventoryClient inventoryClient;
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order create_order(Order order) {
        Integer stock = inventoryClient.getStock(order.getProductId());
        if (order.getQuantity() <= stock) {
            inventoryClient.updateStock(order.getProductId(), stock - order.getQuantity());
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("insufficient stock");
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
