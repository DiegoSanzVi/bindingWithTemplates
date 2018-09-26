package com.vaadin.flow.tutorial.binder.data;

/**
 *
 */
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private String comment;

    public User() {
    }

    /**
     *
     * User's constructor that initializes a new user.
     *
     * @param email email
     * @param firstName first name
     * @param lastName last name
     * @param comment comment about the user
     */
    public User(String email, String firstName, String lastName, String comment) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
