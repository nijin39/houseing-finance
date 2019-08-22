package com.tandem6.housingfinance.creditguaranteesummary.ui;

import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryService;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="/api")
public class CreditGuaranteeSummaryRestController {

    final private CreditGuaranteeSummaryService creditGuaranteeSummaryService;

    public CreditGuaranteeSummaryRestController(CreditGuaranteeSummaryService creditGuaranteeSummaryService) {
        this.creditGuaranteeSummaryService = creditGuaranteeSummaryService;
    }

    @GetMapping("/credit-guarantee-summary/max-amount")
    public CreditGuaranteeSummary findFirstByOrderByAmountAsc(){
        return creditGuaranteeSummaryService.findFirstByOrderByAmountDesc();
    }

}
