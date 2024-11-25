package com.vn.ecommerce.multivendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ErrorDetails> sellerExceptionHandler(SellerException exception, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setError(exception.getMessage());
        details.setDetails(request.getDescription(false));
        details.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException exception, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setError(exception.getMessage());
        details.setDetails(request.getDescription(false));
        details.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
