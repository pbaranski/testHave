package com.vhealth.api.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "user")
public class User extends Audit {
    private static final long serialVersionUID = -2862671438138322400L;

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;


    @Transient
    private DateTime lastLoginOn;

    @Column(name = "is_admin", nullable = true)
    private boolean isAdmin;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DateTime getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(DateTime lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return 13 * userName.hashCode();
    }

}