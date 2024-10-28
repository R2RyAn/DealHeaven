package com.dealheaven;

import java.util.Date;
import java.util.List;

public class Order {

    private String id;
    private String buyerId;
    private List<String> postIds;
    private Date orderDate;
    private double totalPrice;
    private String shippingAddress;
    private String orderStatus;
}
