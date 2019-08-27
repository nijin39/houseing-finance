package com.tandem6.housingfinance.creditguarantee.ui.dto;

import lombok.Data;

@Data
public class PredicateResponse {

    private String bank;
    private int year;
    private int month;
    private int amount;

    public PredicateResponse(String bank, int month, int amount) {
        this.bank = bank;
        this.month = month;
        this.amount = amount;
        this.year = 2018;
    }
}
