package com.tandem6.housingfinance.creditguarantee.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeServiceExcpetion;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportException;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDaoException;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAmountInstitute;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinAverageByInstituteDto;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinYearDto;
import com.tandem6.housingfinance.creditguarantee.ui.dto.PredicateRequest;
import com.tandem6.housingfinance.creditguarantee.ui.dto.PredicateResponse;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class CreditGuaranteeRestController {

    final private CreditGuaranteeService creditGuaranteeService;
    final private AmountReportService amountReportService;
    final private InstituteRepository instituteRepository;

    public CreditGuaranteeRestController(CreditGuaranteeService creditGuaranteeService, AmountReportService amountReportService, InstituteRepository instituteRepository) {
        this.creditGuaranteeService = creditGuaranteeService;
        this.amountReportService = amountReportService;
        this.instituteRepository = instituteRepository;
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
        MaxAmountInstitute maxAmountInstitute = null;
        try {
            maxAmountInstitute = amountReportService.getMaxAmountInstitute();
            return ResponseEntity.ok(maxAmountInstitute);
        } catch (AmountReportException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/creditGuarantee/institute/{instituteName}/max-min-average")
    public ResponseEntity<MaxAndMinAverageByInstituteDto> getMaxAndMinAverage(@PathVariable String instituteName){

        if( instituteName == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            MaxAndMinAverageByInstituteDto maxAndMinAverage = amountReportService.getMaxAndMinAverage(instituteName);
            return ResponseEntity.ok(maxAndMinAverage);
        } catch (AmountReportException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("/creditGuarantee/predicate")
    public ResponseEntity<PredicateResponse> getPredicate(@Valid @RequestBody PredicateRequest predicateRequest, BindingResult result){

        if( result.hasErrors() ){
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Integer creditGuaranteePredicate = creditGuaranteeService.getCreditGuaranteePredicate(predicateRequest.getBank(), predicateRequest.getMonth());
            Optional<Institute> institute = instituteRepository.findByInstituteName(predicateRequest.getBank());

            if( institute.isPresent() ) {
                PredicateResponse predicateResponse = new PredicateResponse(institute.get().getInstituteCode(), predicateRequest.getMonth(), creditGuaranteePredicate);
                return ResponseEntity.ok(predicateResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (CreditGuaranteeServiceExcpetion e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private boolean validatePredicateParameter(@PathVariable String instituteName, @PathVariable Integer month) {
        return instituteName == null || instituteName.isEmpty() || month == 0 || month >= 13;
    }

    @GetMapping("/creditGuarantee/annualReport")
    public ResponseEntity<AmountAnnualReport> getAnnualReport(){
        try {
            AmountAnnualReport amountAnnualReport = amountReportService.generateAmountAnnualReport();
            return ResponseEntity.ok(amountAnnualReport);
        } catch (AmountDaoException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private <T> ResponseEntity<List<T>> getListResponseEntity(List<T> maxAndMinYear) {
        if( maxAndMinYear.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        } else {
            return ResponseEntity.ok(maxAndMinYear);
        }
    }
}
