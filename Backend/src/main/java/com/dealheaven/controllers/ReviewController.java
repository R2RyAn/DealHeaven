package com.dealheaven.controllers;


import com.dealheaven.models.Review;
import com.dealheaven.models.User;
import com.dealheaven.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestBody Review review) throws ExecutionException, InterruptedException {
        ReviewService.createReview(review);
        return ResponseEntity.ok("User created successfully with id: " + review.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }


}
