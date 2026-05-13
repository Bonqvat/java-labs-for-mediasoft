package com.mycompany.laba.service;

import com.mycompany.laba.exception.DuplicateReviewException;
import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Transactional
    public Review createReview(Long userId, Long restaurantId, Review review) {
        User user = userService.getUserById(userId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (reviewRepository.existsByUserAndRestaurant(user, restaurant)) {
            throw new DuplicateReviewException("User has already reviewed this restaurant");
        }

        review.setUser(user);
        review.setRestaurant(restaurant);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByRestaurant(Long restaurantId) {
        restaurantService.getRestaurantById(restaurantId); // ensure exists
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    public List<Review> getReviewsByUser(Long userId) {
        userService.getUserById(userId);
        return reviewRepository.findByUserId(userId);
    }

    @Transactional
    public Review updateReview(Long reviewId, Review reviewDetails) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ResourceNotFoundException("Review not found with id: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}