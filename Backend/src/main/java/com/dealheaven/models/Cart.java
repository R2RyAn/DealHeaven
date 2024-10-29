package com.dealheaven.models;

import java.util.List;

public class Cart {

    private String id;
    private String buyerId;
    private List<Post> items;
    private double totalPrice;

    public void addToCart(Post post){
        items.add(post);
        updateTotalPrice();
    }

    public void removeFromCart(Post post){

        items.remove(post);
        updateTotalPrice();
    }

    public void clearCart(){
        items.clear();
        updateTotalPrice();
    }

    public void updateTotalPrice(){

        for(Post post : items){
            this.totalPrice += post.getPrice();
        }
    }
}
