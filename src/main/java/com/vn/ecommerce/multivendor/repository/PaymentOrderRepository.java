package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLinkId(String paymentId);
}
