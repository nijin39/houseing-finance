package com.tandem6.housingfinance.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tandem6.housingfinance.common.domain.TimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}