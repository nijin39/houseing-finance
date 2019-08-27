package com.tandem6.housingfinance.creditguarantee.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredicateRequest implements Serializable {

    private static final long serialVersionUID = 8085096395374697124L;

    @NotBlank
    private String bank;

    @Range(min = 1L, max = 12L)
    private int month;
}
