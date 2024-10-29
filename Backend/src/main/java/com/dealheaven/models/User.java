package com.dealheaven.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
public class User {


    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private List<Role> roles;
    private String profilePictureUrl;
    private int userRating;


    public User(String id, String name, String password, String email, String phone) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }
    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void updateProfilePicture(String url) {
        this.profilePictureUrl = url;
    }

    public void updateUserProfile(String name, String email, String phone) {
        this.username = name;
        this.email = email;
        this.phone = phone;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public String getUserSummary() {
        return "Username: " + username + "\n" +
                "Email: " + email + "\n" +
                "Roles: " + roles.stream().map(Role::getRoleName).collect(Collectors.joining(", "));
    }

}

