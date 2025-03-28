package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.modal.Deal;
import com.vn.ecommerce.multivendor.response.ApiResponse;
import com.vn.ecommerce.multivendor.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {
    private final DealService dealService;

    @PostMapping()
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
        Deal createdDeal = dealService.createDeal(deal);
        return ResponseEntity.ok(createdDeal);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable Long id, @RequestBody Deal deal) throws Exception {
        Deal updatedDeal = dealService.updateDeal(deal, id);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeals(@PathVariable Long id) throws Exception {
        dealService.deleteDeal(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("deal deleted");

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
