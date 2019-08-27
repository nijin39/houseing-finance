package com.tandem6.housingfinance.account.ui.dto;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 7882639879792029660L;
    private String username;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}