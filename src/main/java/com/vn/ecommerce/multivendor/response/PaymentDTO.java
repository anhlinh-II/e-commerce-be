package com.vn.ecommerce.multivendor.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class PaymentDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VNPayResponse {
        private String code;
        private String message;
        private String paymentUrl;
    }
}
