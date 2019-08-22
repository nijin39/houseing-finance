package com.tandem6.housingfinance.creditguarantee.command.domain;

import java.util.List;

public interface CreditGuaranteePredicate {

    Integer getPredicateAmount(Integer month, List<Long> data);

}
