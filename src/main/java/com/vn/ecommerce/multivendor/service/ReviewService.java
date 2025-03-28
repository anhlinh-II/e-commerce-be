package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.Review;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest request, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewid) throws Exception;
}
