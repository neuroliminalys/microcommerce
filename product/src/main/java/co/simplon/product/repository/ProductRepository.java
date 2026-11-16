package co.simplon.product.repository;

import co.simplon.product.entity.Product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;;

/**
 * ProductRepository
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
    
}