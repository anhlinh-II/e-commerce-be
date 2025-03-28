package com.vn.ecommerce.multivendor.response;

import com.vn.ecommerce.multivendor.modal.Order;
import lombok.Data;

import java.util.Set;

@Data
public class OrderResponse {
    private Long totalMoney;
    private Set<Order> orders;
}
