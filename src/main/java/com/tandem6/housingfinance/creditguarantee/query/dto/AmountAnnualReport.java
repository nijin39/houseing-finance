package com.tandem6.housingfinance.creditguarantee.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountAnnualReport {
    final private String name = "주택금융현황";
    private List<AnnualReport> annualReportList = new ArrayList<>();

    public void addAnnualReport(AnnualReport annualReport){
        this.annualReportList.add(annualReport);
    }
}
