package com.tandem6.housingfinance.creditguaranteesummary.application;

import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditGuaranteeSummaryService {

    final private CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository;

    public CreditGuaranteeSummaryService(CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository) {
        this.creditGuaranteeSummaryRepository = creditGuaranteeSummaryRepository;
    }

    public CreditGuaranteeSummary findFirstByOrderByAmountDesc() throws CreditGuaranteeSummaryExcpetion {
        Optional<CreditGuaranteeSummary> firstByOrderByAmountDesc = creditGuaranteeSummaryRepository.findFirstByOrderByAmountDesc();
        if( firstByOrderByAmountDesc.isPresent() ){
            return firstByOrderByAmountDesc.get();
        } else {
            throw new CreditGuaranteeSummaryExcpetion("결과가 존재하지 않습니다.", 1L);
        }
    }
}
