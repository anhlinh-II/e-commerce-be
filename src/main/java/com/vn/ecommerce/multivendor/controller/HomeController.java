package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ApiResponse HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("welcome to e-commerce multi-vendor system");
        return apiResponse;
    }
}
