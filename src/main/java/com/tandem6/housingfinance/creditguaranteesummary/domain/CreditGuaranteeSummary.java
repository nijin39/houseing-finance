package com.tandem6.housingfinance.creditguaranteesummary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class CreditGuaranteeSummary {

    @EmbeddedId
    private CreditGuaranteeSummaryId creditGuaranteeSummaryId;

    @Column
    private Long amount;

}