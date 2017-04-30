package com.spring.assignment.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;

/**
 * Created by web on 11/04/17.
 */
public class LoginAuthentication {

    // not empty validation before passing data to check user value

    @NotEmpty
    String email;
    @NotEmpty
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
