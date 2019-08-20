package com.tandem6.housingfinance.creditguarantee.query.application;

import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDao;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountReportService {

    final private AmountDao amountDao;

    public AmountReportService(AmountDao amountDao) {
        this.amountDao = amountDao;
    }

    public List<AmountAnnualReport> generateAmountAnnualReport(){
        amountDao.generateAmountAnnualReport();
        return null;
    }
}
