package com.tandem6.housingfinance.creditguaranteesummary.application;

import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryId;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CreditGuaranteeSummaryServiceTest {
    @Mock
    CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository;
    @InjectMocks
    CreditGuaranteeSummaryService creditGuaranteeSummaryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindFirstByOrderByAmountDesc() throws Exception {
        when(creditGuaranteeSummaryRepository.findFirstByOrderByAmountDesc()).thenReturn(null);

        CreditGuaranteeSummary result = creditGuaranteeSummaryService.findFirstByOrderByAmountDesc();
        Assert.assertEquals(new CreditGuaranteeSummary(new CreditGuaranteeSummaryId("instituteName", "year"), Long.valueOf(1)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme