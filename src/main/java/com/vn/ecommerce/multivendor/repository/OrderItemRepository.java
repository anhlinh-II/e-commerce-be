package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
