package com.tandem6.housingfinance.creditguarantee.command.domain;

import com.tandem6.housingfinance.common.domain.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class CreditGuarantee extends TimeEntity implements Serializable {

    @EmbeddedId
    private CreditGuaranteeId creditGuaranteeId;

    @Column
    private Long amount;


}
