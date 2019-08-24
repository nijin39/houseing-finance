package com.tandem6.housingfinance.creditguarantee.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
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

public class CreditGuaranteeRestControllerTest {
    @Mock
    CreditGuaranteeService creditGuaranteeService;
    @Mock
    AmountReportService amountReportService;
    @InjectMocks
    CreditGuaranteeRestController creditGuaranteeRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCreditGuarantees() throws Exception {
        when(creditGuaranteeService.findAllCreditGuarantees()).thenReturn(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))));

        List<CreditGuarantee> result = creditGuaranteeRestController.findAllCreditGuarantees();
        Assert.assertEquals(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))), result);
    }

    @Test
    public void testFindAllCreditGuaranteeYears() throws Exception {
        when(amountReportService.getMaxAndMinYear()).thenReturn(Arrays.<MaxAndMinYearDto>asList(new MaxAndMinYearDto("maxYear", "minYear")));

        List<MaxAndMinYearDto> result = creditGuaranteeRestController.findAllCreditGuaranteeYears();
        Assert.assertEquals(Arrays.<MaxAndMinYearDto>asList(new MaxAndMinYearDto("maxYear", "minYear")), result);
    }

    @Test
    public void testGetMaxAmountInstitute() throws Exception {
        when(amountReportService.getMaxAmountInstitute()).thenReturn(new MaxAmountInstitute(Integer.valueOf(0), "bank"));

        MaxAmountInstitute result = creditGuaranteeRestController.getMaxAmountInstitute();
        Assert.assertEquals(new MaxAmountInstitute(Integer.valueOf(0), "bank"), result);
    }

    @Test
    public void testGetMaxAndMinAverage() throws Exception {
        when(amountReportService.getMaxAndMinAverage(anyString())).thenReturn(new MaxAndMinAverageByInstituteDto("instituteName", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))));

        MaxAndMinAverageByInstituteDto result = creditGuaranteeRestController.getMaxAndMinAverage("instituteName");
        Assert.assertEquals(new MaxAndMinAverageByInstituteDto("instituteName", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))), result);
    }

    @Test
    public void testGetPredicate() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate(anyString(), anyInt())).thenReturn(Integer.valueOf(0));

        Integer result = creditGuaranteeRestController.getPredicate("instituteName", Integer.valueOf(0));
        Assert.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void testGetAnnualReport() throws Exception {
        when(amountReportService.generateAmountAnnualReport()).thenReturn(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))));

        AmountAnnualReport result = creditGuaranteeRestController.getAnnualReport();
        Assert.assertEquals(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme