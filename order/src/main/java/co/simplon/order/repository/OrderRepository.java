package co.simplon.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.order.entity.Order;

/**
 * OrderRepository
 */
public interface OrderRepository extends JpaRepository<Order, UUID>{
}