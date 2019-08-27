package com.tandem6.housingfinance.creditguarantee.ui.dto;

import lombok.Data;

@Data
public class PredicateResponse {

    private String instituteCode;
    private int year;
    private int month;
    private int amount;

    public PredicateResponse(String instituteCode, int month, int amount) {
        this.instituteCode = instituteCode;
        this.month = month;
        this.amount = amount;
        this.year = 2018;
    }
}
