package com.dealheaven.models;

import java.util.Date;

public class Review {
    private String id;
    private String postId;
    private String buyerId;
    private int rating;
    private String comment;
    private Date reviewDate;


    public void createReview(String buyerId, String postId, int rating, String comment) {
        this.postId = postId;
        this.buyerId = buyerId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = new Date();
    }

    public void updateReview(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewDetails() {
        return "Rating: " + rating + "/5\n" +
                "Comment: " + comment + "\n" +
                "Date: " + reviewDate;
    }


}
