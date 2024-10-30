package com.dealheaven;

import com.dealheaven.models.Role;
import com.dealheaven.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;
    private Role role;

    @BeforeEach
    public void setUp() {
        user = new User("1", "username", "password", "email@example.com", "1234567890");
        user.setRoles(new ArrayList<>());
        role = new Role();
        role.assignRole("ROLE_USER");
    }

    @Test
    public void testAddRole() {
        user.addRole(role);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void testRemoveRole() {
        user.addRole(role);
        user.removeRole(role);
        assertEquals(0, user.getRoles().size());
    }

    @Test
    public void testUpdateProfilePicture() {
        user.updateProfilePicture("newPicUrl");
        assertEquals("newPicUrl", user.getProfilePictureUrl());
    }

    @Test
    public void testUpdateUserProfile() {
        user.updateUserProfile("newUsername", "newEmail@example.com", "0987654321");
        assertEquals("newUsername", user.getUsername());
        assertEquals("newEmail@example.com", user.getEmail());
        assertEquals("0987654321", user.getPhone());
    }

    @Test
    public void testChangePassword() {
        user.changePassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testGetUserSummary() {
        user.addRole(role);
        String summary = user.getUserSummary();
        assertEquals("Username: username\nEmail: email@example.com\nRoles: ROLE_USER", summary);
    }
}