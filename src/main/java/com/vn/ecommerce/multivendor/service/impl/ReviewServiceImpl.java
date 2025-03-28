package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.Product;
import com.vn.ecommerce.multivendor.modal.Review;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.ReviewRepository;
import com.vn.ecommerce.multivendor.request.CreateReviewRequest;
import com.vn.ecommerce.multivendor.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest request, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(request.getReviewText());
        review.setRating(request.getReviewRating());
        review.setProductImages(request.getProductImages());

        product.getReviews().add(review);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (review.getUser().getId().equals(userId)) {
            review.setReviewText(reviewText);
            review.setRating(rating);

            return reviewRepository.save(review);
        }
        throw new Exception("you can not update this review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if (!review.getUser().getId().equals(userId)) {
            throw new Exception ("you can not delete this review");
        }
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new Exception("review not found"));
    }
}
