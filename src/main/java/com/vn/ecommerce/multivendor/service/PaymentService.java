package com.vn.ecommerce.multivendor.service;

import java.util.Set;

import com.vn.ecommerce.multivendor.modal.Order;
import com.vn.ecommerce.multivendor.modal.PaymentOrder;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.request.PaymentRequest;
import com.vn.ecommerce.multivendor.response.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
     PaymentOrder createPaymentOrder(User user, Set<Order> orders);
     PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
     PaymentOrder getPaymentOrderByPaymentId(String id) throws Exception;
     Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId);

     PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, PaymentRequest paymentRequest);
}
