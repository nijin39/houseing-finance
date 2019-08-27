package com.tandem6.housingfinance.creditguarantee.query.application;

import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDao;
import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDaoException;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAmountInstitute;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinAverageByInstituteDto;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinYearDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AmountReportService {

    final private AmountDao amountDao;

    public AmountReportService(AmountDao amountDao) {
        this.amountDao = amountDao;
    }

    public AmountAnnualReport generateAmountAnnualReport() throws AmountDaoException {
        return amountDao.generateAmountAnnualReport();
    }

    public MaxAmountInstitute getMaxAmountInstitute() throws AmountReportException {
        try {
            MaxAmountInstitute maxAmountInstitute = amountDao.getMaxAmountInstitute();
            return maxAmountInstitute;
        } catch (NoSuchElementException e){
            throw new AmountReportException(e, 1L);
        }
    }

    public MaxAndMinAverageByInstituteDto getMaxAndMinAverage(String instituteName) throws AmountReportException {
        try {
            return amountDao.getMaxAndMinAverage(instituteName);
        } catch (NoSuchElementException e){
            throw new AmountReportException(e, 2L);
        }
    }

    public List<MaxAndMinYearDto> getMaxAndMinYear(){
        return amountDao.getMaxAndMinYear();
    }
}
