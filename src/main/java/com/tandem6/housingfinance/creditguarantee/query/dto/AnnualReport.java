package com.tandem6.housingfinance.creditguarantee.query.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class AnnualReport {
    private Integer year;
    private Integer total_amount;
    private Map<String, Integer> detail_amount;
}
