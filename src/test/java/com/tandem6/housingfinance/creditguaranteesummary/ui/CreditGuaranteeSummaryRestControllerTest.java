package com.tandem6.housingfinance.creditguaranteesummary.ui;

import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryService;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CreditGuaranteeSummaryRestControllerTest {
    @Mock
    CreditGuaranteeSummaryService creditGuaranteeSummaryService;
    @InjectMocks
    CreditGuaranteeSummaryRestController creditGuaranteeSummaryRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindFirstByOrderByAmountAsc() throws Exception {
        when(creditGuaranteeSummaryService.findFirstByOrderByAmountDesc()).thenReturn(new CreditGuaranteeSummary(new CreditGuaranteeSummaryId("instituteName", "year"), Long.valueOf(1)));

        CreditGuaranteeSummary result = creditGuaranteeSummaryRestController.findFirstByOrderByAmountAsc();
        Assert.assertEquals(new CreditGuaranteeSummary(new CreditGuaranteeSummaryId("instituteName", "year"), Long.valueOf(1)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme