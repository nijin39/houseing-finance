package com.tandem6.housingfinance.creditguarantee.query.application;

import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDao;
import com.tandem6.housingfinance.creditguarantee.query.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class AmountReportServiceTest {
    @Mock
    AmountDao amountDao;
    @InjectMocks
    AmountReportService amountReportService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateAmountAnnualReport() throws Exception {
        when(amountDao.generateAmountAnnualReport()).thenReturn(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))));

        AmountAnnualReport result = amountReportService.generateAmountAnnualReport();
        Assert.assertEquals(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))), result);
    }

    @Test
    public void testGetMaxAmountInstitute() throws Exception {
        when(amountDao.getMaxAmountInstitute()).thenReturn(new MaxAmountInstitute(Integer.valueOf(0), "bank"));

        MaxAmountInstitute result = amountReportService.getMaxAmountInstitute();
        Assert.assertEquals(new MaxAmountInstitute(Integer.valueOf(0), "bank"), result);
    }

    @Test
    public void testGetMaxAndMinAverage() throws Exception {
        when(amountDao.getMaxAndMinAverage(anyString())).thenReturn(new MaxAndMinAverageByInstituteDto("instituteName", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))));

        MaxAndMinAverageByInstituteDto result = amountReportService.getMaxAndMinAverage("instituteName");
        Assert.assertEquals(new MaxAndMinAverageByInstituteDto("instituteName", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))), result);
    }

    @Test
    public void testGetMaxAndMinYear() throws Exception {
        when(amountDao.getMaxAndMinYear()).thenReturn(Arrays.<MaxAndMinYearDto>asList(new MaxAndMinYearDto("maxYear", "minYear")));

        List<MaxAndMinYearDto> result = amountReportService.getMaxAndMinYear();
        Assert.assertEquals(Arrays.<MaxAndMinYearDto>asList(new MaxAndMinYearDto("maxYear", "minYear")), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme