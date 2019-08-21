package com.tandem6.housingfinance.creditguaranteesummary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreditGuaranteeSummaryId implements Serializable {
    @Column(name="institute_name")
    private String instituteName;

    @Column(name="year")
    private String year;
}
