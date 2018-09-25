package demo;

import java.util.Objects;

public class UserComment {

    private String email;
    private String firstName;
    private String lastName;
    private String comment;

    public UserComment() {
    }

    public UserComment(String email, String firstName, String lastName, String comment) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserComment)) return false;
        UserComment comment = (UserComment) o;
        return Objects.equals(getEmail(), comment.getEmail()) &&
                Objects.equals(getFirstName(), comment.getFirstName()) &&
                Objects.equals(getLastName(), comment.getLastName()) &&
                Objects.equals(getComment(), comment.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getComment());
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
