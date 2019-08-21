package com.tandem6.housingfinance.creditguarantee.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@ToString
@Data
@AllArgsConstructor
public class CreditGuaranteeSummary {

    private String instituteName;
    private String year;
    private BigDecimal total;

    public int getTotal() {
        return total.intValue();
    }
}
