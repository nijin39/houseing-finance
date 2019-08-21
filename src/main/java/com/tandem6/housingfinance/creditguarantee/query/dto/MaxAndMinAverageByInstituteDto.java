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
public class MaxAndMinAverageByInstituteDto {
    private String instituteName;
    private List<AmountByYear> support_amount = new ArrayList<>();

    public MaxAndMinAverageByInstituteDto(String instituteName) {
        this.instituteName = instituteName;
    }

    public void addSupportAmount(AmountByYear amountByYear){
        support_amount.add(amountByYear);
    }
}
