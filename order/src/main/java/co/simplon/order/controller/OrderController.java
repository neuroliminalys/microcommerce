package co.simplon.order.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.order.entity.Order;
import co.simplon.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order newOrder) {
        try {
            Order orderCreated = this.orderService.create_order(newOrder);
            return ResponseEntity.ok(orderCreated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Insufficient stock");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
}
