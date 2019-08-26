package com.tandem6.housingfinance.creditguaranteesummary.ui;

import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryExcpetion;
import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryService;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CreditGuaranteeSummaryRestController {

    final private CreditGuaranteeSummaryService creditGuaranteeSummaryService;

    public CreditGuaranteeSummaryRestController(CreditGuaranteeSummaryService creditGuaranteeSummaryService) {
        this.creditGuaranteeSummaryService = creditGuaranteeSummaryService;
    }

    @GetMapping("/credit-guarantee-summary/max-amount")
    public ResponseEntity<CreditGuaranteeSummary> findFirstByOrderByAmountAsc() {

        try {
            CreditGuaranteeSummary firstByOrderByAmountDesc = creditGuaranteeSummaryService.findFirstByOrderByAmountDesc();
            return ResponseEntity.status(HttpStatus.OK).body(firstByOrderByAmountDesc);
        } catch (CreditGuaranteeSummaryExcpetion creditGuaranteeSummaryExcpetion) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

}
