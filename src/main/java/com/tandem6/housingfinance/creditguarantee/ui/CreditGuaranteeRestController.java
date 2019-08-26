package com.tandem6.housingfinance.creditguarantee.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAmountInstitute;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinAverageByInstituteDto;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinYearDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
    public ResponseEntity<List<CreditGuarantee>> findAllCreditGuarantees(){

        List<CreditGuarantee> allCreditGuarantees = creditGuaranteeService.findAllCreditGuarantees();
        return getListResponseEntity(allCreditGuarantees);
    }

    @GetMapping("/creditGuarantee/years")
    public ResponseEntity<List<MaxAndMinYearDto>> findAllCreditGuaranteeYears(){

        List<MaxAndMinYearDto> maxAndMinYear = amountReportService.getMaxAndMinYear();
        return getListResponseEntity(maxAndMinYear);
    }

    @GetMapping("/creditGuarantee/institute/max-amount")
    public ResponseEntity<MaxAmountInstitute> getMaxAmountInstitute(){
        MaxAmountInstitute maxAmountInstitute = amountReportService.getMaxAmountInstitute();
        return ResponseEntity.ok(maxAmountInstitute);
    }

    @GetMapping("/creditGuarantee/institute/{instituteName}/max-min-average")
    public MaxAndMinAverageByInstituteDto getMaxAndMinAverage(@PathVariable String instituteName){
        return amountReportService.getMaxAndMinAverage(instituteName);
    }

    @GetMapping("/creditGuarantee/{instituteName}/{month}/predicate")
    public Integer getPredicate(@PathVariable String instituteName, @PathVariable Integer month ){
        try {
            return creditGuaranteeService.getCreditGuaranteePredicate(instituteName, month);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping("/creditGuarantee/annualReport")
    public AmountAnnualReport getAnnualReport(){
        return amountReportService.generateAmountAnnualReport();
    }

    private <T> ResponseEntity<List<T>> getListResponseEntity(List<T> maxAndMinYear) {
        if( maxAndMinYear.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        } else {
            return ResponseEntity.ok(maxAndMinYear);
        }
    }
}
