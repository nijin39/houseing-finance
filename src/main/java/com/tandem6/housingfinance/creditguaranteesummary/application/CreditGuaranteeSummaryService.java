package com.tandem6.housingfinance.creditguaranteesummary.application;

import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditGuaranteeSummaryService {

    final private CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository;

    public CreditGuaranteeSummaryService(CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository) {
        this.creditGuaranteeSummaryRepository = creditGuaranteeSummaryRepository;
    }

    public CreditGuaranteeSummary findFirstByOrderByAmountDesc(){
        return creditGuaranteeSummaryRepository.findFirstByOrderByAmountDesc().get();
    }
}
