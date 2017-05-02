package com.spring.assignment.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // auto increment user id
    Long id;

    @NotEmpty @Size(min=1, max=30)  // not empty to all values and set string length constraints
    String fname;
    @NotEmpty @Size(min=1, max=30)
    String lname;
    @NotEmpty @Email // validation user email but be an email format
    String email;
    @NotEmpty @Size(min=5, max=30)  // set minimum value for stronger password
    String password;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
