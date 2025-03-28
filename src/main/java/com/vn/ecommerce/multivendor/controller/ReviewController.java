package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.Review;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.request.CreateReviewRequest;
import com.vn.ecommerce.multivendor.response.ApiResponse;
import com.vn.ecommerce.multivendor.service.ProductService;
import com.vn.ecommerce.multivendor.service.ReviewService;
import com.vn.ecommerce.multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/product/{productId}/reviews")
    public ResponseEntity<Review> writeReview(@RequestBody CreateReviewRequest request, @PathVariable Long productId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(productId);

        Review review = reviewService.createReview(request, user, product);

        return ResponseEntity.ok(review);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@RequestBody CreateReviewRequest request, @PathVariable Long reviewId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Review review = reviewService.updateReview(reviewId, request.getReviewText(), request.getReviewRating(), user.getId());

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse response = new ApiResponse();
        response.setMessage("Review deleted successfully!");

        return ResponseEntity.ok(response);
    }
}
