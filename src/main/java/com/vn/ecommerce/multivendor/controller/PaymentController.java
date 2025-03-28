package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.request.PaymentRequest;
import com.vn.ecommerce.multivendor.response.PaymentDTO;
import com.vn.ecommerce.multivendor.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/vn-pay")
    public ResponseEntity<PaymentDTO.VNPayResponse> pay(HttpServletRequest request, PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.createVnPayPayment(request, paymentRequest), HttpStatus.OK);
    }

    @GetMapping("/vn-pay-callback")
    public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        String status = request.getParameter("vnp_ResponseCode");
        log.info("Payment callback received, status: {}", status);

        if ("00".equals(status)) { // Payment successful
            response.sendRedirect("http://localhost:3000/payment-success"); // Redirect to frontend success page
        } else { // Payment failed
            response.sendRedirect("http://localhost:3000/payment-failed");
        }
    }



}