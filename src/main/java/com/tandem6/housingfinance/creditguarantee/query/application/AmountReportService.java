package com.tandem6.housingfinance.creditguarantee.query.application;

import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDao;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAmountInstitute;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinAverageByInstituteDto;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinYearDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountReportService {

    final private AmountDao amountDao;

    public AmountReportService(AmountDao amountDao) {
        this.amountDao = amountDao;
    }

    public AmountAnnualReport generateAmountAnnualReport(){
        return amountDao.generateAmountAnnualReport();
    }

    public MaxAmountInstitute getMaxAmountInstitute() {
        MaxAmountInstitute maxAmountInstitute = amountDao.getMaxAmountInstitute();
        return maxAmountInstitute;
    }

    public MaxAndMinAverageByInstituteDto getMaxAndMinAverage(String instituteName){
        return amountDao.getMaxAndMinAverage(instituteName);
    }

    public List<MaxAndMinYearDto> getMaxAndMinYear(){
        return amountDao.getMaxAndMinYear();
    }
}
