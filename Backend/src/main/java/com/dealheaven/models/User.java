package com.dealheaven.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a user in the system.
 * All user information are going to be stored and initialized here.
 */
@Setter
@Getter
public class User {

    /**
     * Unique ID for the user
     */
    @Id
    private String id;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Phone number of the user.
     */
    private String phone;

    /**
     * List of roles assigned to the user.
     */
    private List<Role> roles;

    /**
     * URL of the user's profile picture.
     */
    private String profilePictureUrl;

    /**
     * Rating of the user.
     */
    private int userRating;

    /**
     * List of post IDs associated with the user.
     */
    private List<String> postIds;

    /**
     * Default constructor initializes the roles and postIds lists for the user.
     */
    public User() {
        this.roles = new ArrayList<>();
        this.postIds = new ArrayList<>();
    }

    /**
     * Constructs a User with specified id, name, password, email, and phone.
     *
     * @param id the unique ID for the user.
     * @param name the username of the user.
     * @param password the password of the user.
     * @param email the email address of the user.
     * @param phone the phone number of the user.
     */
    public User(String id, String name, String password, String email, String phone) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = new ArrayList<>();
        this.postIds = new ArrayList<>();
    }

    /**
     * Adds a role to the user.
     *
     * @param role the role to add to the user.
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Removes a role from the user.
     *
     * @param role the role to remove from the user.
     */
    public void removeRole(Role role) {
        roles.remove(role);
    }

    /**
     * Updates the profile picture URL of the user.
     *
     * @param url the new URL for the user's profile picture.
     */
    public void updateProfilePicture(String url) {
        this.profilePictureUrl = url;
    }

    /**
     * Updates the user's profile information, including username, email, and phone number.
     *
     * @param name the new username.
     * @param email the new email address.
     * @param phone the new phone number.
     */
    public void updateUserProfile(String name, String email, String phone) {
        this.username = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Changes the user's password.
     *
     * @param password the new password.
     */
    public void changePassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves a summary of the user's information, including username, email, and roles.
     *
     * @return a summary string containing user details.
     */
    public String getUserSummary() {
        return "Username: " + username + "\n" +
                "Email: " + email + "\n" +
                "Roles: " + roles.stream().map(Role::getRoleName).collect(Collectors.joining(", "));
    }

    /**
     * Adds a post ID to the user's list of associated posts.
     *
     * @param postId the ID of the post to add.
     */
    public void addPostId(String postId) {
        this.postIds.add(postId);
    }
}
