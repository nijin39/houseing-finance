package com.tandem6.housingfinance.creditguarantee.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CreditGuaranteeRestController {

    final private CreditGuaranteeService creditGuaranteeService;
    final private AmountReportService amountReportService;

    public CreditGuaranteeRestController(CreditGuaranteeService creditGuaranteeService, AmountReportService amountReportService) {
        this.creditGuaranteeService = creditGuaranteeService;
        this.amountReportService = amountReportService;
    }

    @GetMapping("/creditGuarantees")
    public List<CreditGuarantee> findAllCreditGuarantees(){
        return creditGuaranteeService.findAllCreditGuarantees();
    }

    @GetMapping("/creditGuarantee/annualReport")
    public List<AmountAnnualReport> getAnnualReport(){
        amountReportService.generateAmountAnnualReport();
        return null;
    }
}
