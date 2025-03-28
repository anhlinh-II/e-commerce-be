package com.vn.ecommerce.multivendor.service.impl;

import java.util.Map;
import java.util.Set;

import com.vn.ecommerce.multivendor.config.VNPAYConfig;
import com.vn.ecommerce.multivendor.domain.PaymentOrderStatus;
import com.vn.ecommerce.multivendor.repository.OrderRepository;
import com.vn.ecommerce.multivendor.repository.PaymentOrderRepository;
import com.vn.ecommerce.multivendor.request.PaymentRequest;
import com.vn.ecommerce.multivendor.response.PaymentDTO;
import com.vn.ecommerce.multivendor.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.vn.ecommerce.multivendor.modal.Order;
import com.vn.ecommerce.multivendor.modal.PaymentOrder;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
     private final OrderRepository orderRepository;
     private final PaymentOrderRepository paymentOrderRepository;

     private String apiKey = "apiKey";
     private String apiSecret = "apiSecret";

     private final VNPAYConfig vnPayConfig;

     public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, PaymentRequest paymentRequest) {
          long amount = Integer.parseInt(paymentRequest.getAmount()) * 100L;
          String bankCode = paymentRequest.getBankCode();
          Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
          vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
          if (bankCode != null && !bankCode.isEmpty()) {
               vnpParamsMap.put("vnp_BankCode", bankCode);
          }
          vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
          //build query url
          String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
          String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
          String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
          queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
          String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
          return PaymentDTO.VNPayResponse.builder()
                  .code("ok")
                  .message("success")
                  .paymentUrl(paymentUrl).build();
     }

     @Override
     public PaymentOrder createPaymentOrder(User user, Set<Order> orders) {
          Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

          PaymentOrder paymentOrder = new PaymentOrder();
          paymentOrder.setAmount(amount);
          paymentOrder.setUser(user);
          paymentOrder.setOrders(orders);
          return paymentOrderRepository.save(paymentOrder);
     }

     @Override
     public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {
          return paymentOrderRepository.findById(orderId)
                  .orElseThrow(() -> new Exception("payment order not found"));
     }

     @Override
     public PaymentOrder getPaymentOrderByPaymentId(String id) throws Exception {
          PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentLinkId(id);
          if (paymentOrder == null) {
               throw new Exception("payment order not found with provided link id");
          }
          return paymentOrder;
     }

     @Override
     public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId){
          if (paymentOrder.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING)) {

          }
          return null;
     }


}
