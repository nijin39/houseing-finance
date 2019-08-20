package com.tandem6.housingfinance.creditguarantee.query.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class AmountAnnualReport {
    final private String name = "주택금융현황";
    private String year;
    private String total_amount;
    private Map<String, Long> detail_amount;
}
