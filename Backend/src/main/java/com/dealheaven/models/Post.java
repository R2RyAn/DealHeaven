package com.dealheaven.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Post {


    private String id;
    private String title;
    private String description;
    private double price;
    private String make;
    private String model;
    private int year;
    private int SeatingCapacity;
    private String condition;
    private String sellerId;
    private String category;
    private List<String> images;
    private boolean isAvailable;


    public void markAsSold(){
        this.isAvailable = false;
    }

    public void updatePostDetails(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void addImage(String imageUrl){
        images.add(imageUrl);
    }
    public void removeImage(String imageUrl) {
        images.remove(imageUrl);
    }


}
