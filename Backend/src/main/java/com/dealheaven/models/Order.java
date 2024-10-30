package com.dealheaven.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private String id;
    private String buyerId;
    private List<Post> items;
    private Date orderDate;
    private double totalPrice;
    private String shippingAddress;
    private String orderStatus;

    public void placeOrder(List<Post> items, double totalPrice){
        this.items = new ArrayList<>(items);
        this.totalPrice = totalPrice;
        this.orderDate = new Date();
        this.orderStatus = "Placed"; // Shipped, Delivered or Placed
    }

    public void updateOrderStatus(String status){
        this.orderStatus = status;
    }

    public String getOrderSummary() {
        return "Order ID: " + id + "\n" +
                "Total Price: " + totalPrice + "\n" +
                "Items: " + items.size() + "\n" +
                "Status: " + orderStatus;
    }

}
