package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.domain.AccountStatus;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final SellerService sellerService;

    @PatchMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updatedSellerStatus(@PathVariable Long id, @PathVariable AccountStatus status) throws Exception {
        Seller seller = sellerService.updateSellerAccountStatus(id, status);
        return ResponseEntity.ok(seller);
    }
}
