package com.tandem6.housingfinance.creditguarantee.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AmountByYear {
    private Integer year;
    private Integer amount;
}
