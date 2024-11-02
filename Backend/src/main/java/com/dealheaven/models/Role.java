/**
 * Represents a user role in dealheaven
 * Option to either be SElLER, BUYER or BOTH
 */
package com.dealheaven.models;

public class Role {

    /**
     * The name of the role.
     */
    private String name;

    /**
     * Assigns a name to the role.
     *
     * @param roleName The name to assign to the role.
     */
    public void assignRole(String roleName) {
        this.name = roleName;
    }

    /**
     * returns the name of the role.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return name;
    }
}
