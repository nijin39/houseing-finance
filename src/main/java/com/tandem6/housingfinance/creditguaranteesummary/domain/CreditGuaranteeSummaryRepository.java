package com.tandem6.housingfinance.creditguaranteesummary.domain;

import java.util.Optional;

public interface CreditGuaranteeSummaryRepository {
    Optional<CreditGuaranteeSummary> findFirstByOrderByAmountDesc();
    CreditGuaranteeSummary save(CreditGuaranteeSummary creditGuaranteeSummary);
    Optional<CreditGuaranteeSummary> findByCreditGuaranteeSummaryId(CreditGuaranteeSummaryId creditGuaranteeSummaryId);
}
