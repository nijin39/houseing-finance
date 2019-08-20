package com.tandem6.housingfinance.creditguarantee.command.domain;

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
public class CreditGuaranteeId implements Serializable {

    @Column(name="institute_code")
    private String instituteCode;

    @Column(name="year")
    private String year;

    @Column(name="month")
    private String month;
}
