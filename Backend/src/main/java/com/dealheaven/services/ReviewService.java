package com.dealheaven.services;


import com.dealheaven.models.Review;
import com.dealheaven.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewService {

    private static final String REVIEW_COLLECTION = "reviews";

    public static void createReview(Review review) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection(REVIEW_COLLECTION);

        ApiFuture<QuerySnapshot> emailQuery = users.whereEqualTo("comment", review.getComment()).get();
        List<QueryDocumentSnapshot> emailDocuments = emailQuery.get().getDocuments();
        ApiFuture<QuerySnapshot> phoneQuery = users.whereEqualTo("rating", review.getRating()).get();
        List<QueryDocumentSnapshot> phonedDocuments = phoneQuery.get().getDocuments();

        if(!emailDocuments.isEmpty()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

        if(!phonedDocuments.isEmpty()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }


        // Add user and retrieve the document reference
        DocumentReference documentReference = users.add(review).get();
        // Set the Firebase-generated ID to the user object
        String generatedId = documentReference.getId();
        review.setId(generatedId);
        // Optionally update the user document with the generated ID
        documentReference.set(review).get();
    }

    public Review getReviewById(String reviewId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference ReviewDoc = db.collection(REVIEW_COLLECTION).document(reviewId);
        return ReviewDoc.get().get().toObject(Review.class);
    }
}
