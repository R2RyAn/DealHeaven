package com.dealheaven.models;

public class Role {

    //Either "ROLE_SELLER", or "ROLE_BUYER"
    private String name;

    public void assignRole(String roleName){
        this.name = roleName;
    }

    public String getRoleName() {
        return name;
    }

}