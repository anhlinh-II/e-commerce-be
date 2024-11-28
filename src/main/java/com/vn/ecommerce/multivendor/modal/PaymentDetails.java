package com.vn.ecommerce.multivendor.modal;

import com.vn.ecommerce.multivendor.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private PaymentStatus status;
}
