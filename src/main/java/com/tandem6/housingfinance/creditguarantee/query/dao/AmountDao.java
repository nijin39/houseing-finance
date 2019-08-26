package com.tandem6.housingfinance.creditguarantee.query.dao;

import com.tandem6.housingfinance.creditguarantee.query.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.executor.JpaQueryExecutor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class AmountDao {

    final private EntityManager entityManager;

    public AmountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<MaxAndMinYearDto> getMaxAndMinYear() {
        JpaQueryExecutor queryExecutor = new JpaQueryExecutor();
        return queryExecutor.executeSelect(entityManager, MaxAndMinYearDto.class, "query/findMaxAndMinYear.sql");
    }

    public MaxAndMinAverageByInstituteDto getMaxAndMinAverage(String instituteName){
        JpaQueryExecutor queryExecutor = new JpaQueryExecutor();
        List<CreditGuaranteeSummary> creditGuaranteeSummaryList = queryExecutor.executeSelect(entityManager, CreditGuaranteeSummary.class, "query/AllCreditGuaranteeSummary.sql");
        CreditGuaranteeSummary maxCreditGuaranteeSummary = maxCreditGuaranteeSummary(instituteName, creditGuaranteeSummaryList);
        CreditGuaranteeSummary minCreditGuaranteeSummary = getCreditGuaranteeMinAmount(instituteName, creditGuaranteeSummaryList);
        MaxAndMinAverageByInstituteDto maxAndMinAverageByInstituteDto = convertMaxAndMinAverageByInstituteDto(instituteName, maxCreditGuaranteeSummary, minCreditGuaranteeSummary);

        return maxAndMinAverageByInstituteDto;
    }

    private MaxAndMinAverageByInstituteDto convertMaxAndMinAverageByInstituteDto(String instituteName, CreditGuaranteeSummary maxCreditGuaranteeSummary, CreditGuaranteeSummary minCreditGuaranteeSummary) {
        //DTO 변환
        MaxAndMinAverageByInstituteDto maxAndMinAverageByInstituteDto = new MaxAndMinAverageByInstituteDto(instituteName);
        maxAndMinAverageByInstituteDto.addSupportAmount(                              //16.6
                new AmountByYear(Integer.valueOf(maxCreditGuaranteeSummary.getYear()), Math.round((float)maxCreditGuaranteeSummary.getTotal()/12))
        );
        maxAndMinAverageByInstituteDto.addSupportAmount(
                new AmountByYear(Integer.valueOf(minCreditGuaranteeSummary.getYear()), Math.round((float)minCreditGuaranteeSummary.getTotal()/12))
        );
        return maxAndMinAverageByInstituteDto;
    }

    private CreditGuaranteeSummary maxCreditGuaranteeSummary(String instituteName, List<CreditGuaranteeSummary> creditGuaranteeSummaryList) {
        return creditGuaranteeSummaryList.stream()
                    .filter(creditGuaranteeSummaryItem -> creditGuaranteeSummaryItem.getInstituteName().equals(instituteName))
                    .max(Comparator.comparing(CreditGuaranteeSummary::getTotal))
                    .orElseThrow(NoSuchElementException::new);
    }

    private CreditGuaranteeSummary getCreditGuaranteeMinAmount(String instituteName, List<CreditGuaranteeSummary> creditGuaranteeSummaryList) {
        return creditGuaranteeSummaryList.stream()
                    .filter(creditGuaranteeSummaryItem -> creditGuaranteeSummaryItem.getInstituteName().equals(instituteName))
                    .min(Comparator.comparing(CreditGuaranteeSummary::getTotal))
                    .orElseThrow(NoSuchElementException::new);
    }


    public MaxAmountInstitute getMaxAmountInstitute(){
        JpaQueryExecutor queryExecutor = new JpaQueryExecutor();
        List<CreditGuaranteeSummary> creditGuaranteeSummaryList = queryExecutor.executeSelect(entityManager, CreditGuaranteeSummary.class, "query/AllCreditGuaranteeSummary.sql");
        CreditGuaranteeSummary creditGuaranteeSummary = creditGuaranteeSummaryList.stream()
                .max(Comparator.comparing(CreditGuaranteeSummary::getTotal))
                .orElseThrow(NoSuchElementException::new);
        return new MaxAmountInstitute(Integer.valueOf(creditGuaranteeSummary.getYear()), creditGuaranteeSummary.getInstituteName());
    }

    public AmountAnnualReport generateAmountAnnualReport() {
        AmountAnnualReport amountAnnualReport = new AmountAnnualReport();

        MaxAndMinYearDto maxAndMinYearDto = this.getMaxAndMinYear().get(0);
        JpaQueryExecutor queryExecutor = new JpaQueryExecutor();

        IntStream.range(Integer.valueOf(maxAndMinYearDto.getMinYear()), Integer.valueOf(maxAndMinYearDto.getMaxYear())+1)
                .forEach(year -> {
                    List<CreditGuaranteeSummary> creditGuaranteeSummaryList = queryExecutor.executeSelect(entityManager, CreditGuaranteeSummary.class, "query/sample.sql", String.valueOf(year));

                    amountAnnualReport.addAnnualReport(
                            AnnualReport.builder()
                                    .year(year)
                                    .total_amount(getTotalAmountByYear(creditGuaranteeSummaryList))
                                    .detail_amount(getInstituteAmountByYear(creditGuaranteeSummaryList))
                                    .build()
                    );
                });
        return amountAnnualReport;
    }

    private Map<String, Integer> getInstituteAmountByYear(List<CreditGuaranteeSummary> list) {
        return list.stream().collect(
                Collectors.toMap(CreditGuaranteeSummary::getInstituteName, CreditGuaranteeSummary::getTotal));
    }

    private Integer getTotalAmountByYear(List<CreditGuaranteeSummary> list) {
        return list.stream().map(CreditGuaranteeSummary::getTotal)
                .reduce(0, (a, b) -> a + b);
    }
}
