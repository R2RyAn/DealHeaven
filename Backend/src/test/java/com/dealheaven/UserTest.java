package com.dealheaven;

import com.dealheaven.models.Role;
import com.dealheaven.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the User class in the Deal Heaven application.
 * This class makes sure all methods and functionalities of the User class are working as expected.
 */
public class UserTest {

    private User user;
    private Role role;

    /**
     * Initializes a new User instance and assigns a default role before each test.
     */
    @BeforeEach
    public void setUp() {
        user = new User("1", "username", "password", "email@example.com", "1234567890");
        user.setRoles(new ArrayList<>());
        role = new Role();
        role.assignRole("BUYER");
    }

    /**
     * Tests adding a role to a user.
     * Verifies that the user's role list size increases by 1 after adding a role.
     */
    @Test
    public void testAddRole() {
        user.addRole(role);
        assertEquals(1, user.getRoles().size());
    }

    /**
     * Tests removing a role from a user.
     * Verifies that the user's role list size decreases by 1 after removing a role.
     */
    @Test
    public void testRemoveRole() {
        user.addRole(role);
        user.removeRole(role);
        assertEquals(0, user.getRoles().size());
    }

    /**
     * Tests updating the profile picture URL of a user.
     * Verifies that the profile picture URL is correctly updated.
     */
    @Test
    public void testUpdateProfilePicture() {
        user.updateProfilePicture("newPicUrl");
        assertEquals("newPicUrl", user.getProfilePictureUrl());
    }

    /**
     * Tests updating the user's profile information, including username, email, and phone number.
     * Verifies that each field is correctly updated with the new values.
     */
    @Test
    public void testUpdateUserProfile() {
        user.updateUserProfile("newUsername", "newEmail@example.com", "0987654321");
        assertEquals("newUsername", user.getUsername());
        assertEquals("newEmail@example.com", user.getEmail());
        assertEquals("0987654321", user.getPhone());
    }

    /**
     * Tests changing the user's password.
     * Verifies that the password is correctly updated with the new password.
     */
    @Test
    public void testChangePassword() {
        user.changePassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    /**
     * Tests generating a summary of the user's information, including username, email, and roles.
     * Verifies that the summary string is correctly formatted and includes the assigned roles.
     */
    @Test
    public void testGetUserSummary() {
        user.addRole(role);
        String summary = user.getUserSummary();
        assertEquals("Username: username\nEmail: email@example.com\nRoles: BUYER", summary);
    }
}
