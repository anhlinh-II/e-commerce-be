package com.vn.ecommerce.multivendor.request;

import lombok.Data;

@Data
public class AddItemRequest {
    private String size;
    private int quantity;
    private Long ProductId;
}
