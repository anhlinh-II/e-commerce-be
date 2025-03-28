package com.vn.ecommerce.multivendor.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private String amount;
    private String bankCode;
}
