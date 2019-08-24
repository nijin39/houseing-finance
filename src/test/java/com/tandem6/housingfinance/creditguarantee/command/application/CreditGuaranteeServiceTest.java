package com.tandem6.housingfinance.creditguarantee.command.application;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteePredicate;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CreditGuaranteeServiceTest {
    @Mock
    CreditGuaranteeRepository creditGuaranteeRepository;
    @Mock
    InstituteRepository instituteRepository;
    @Mock
    CreditGuaranteePredicate creditGuaranteePredicate;
    @Mock
    Logger log;
    @InjectMocks
    CreditGuaranteeService creditGuaranteeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCreditGuarantees() throws Exception {
        when(creditGuaranteeRepository.findAll()).thenReturn(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))));

        List<CreditGuarantee> result = creditGuaranteeService.findAllCreditGuarantees();
        Assert.assertEquals(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))), result);
    }

    @Test
    public void testImportCsv() throws Exception {
        creditGuaranteeService.importCsv(null);
    }

    @Test
    public void testGetCreditGuaranteePredicate() throws Exception {
        when(creditGuaranteeRepository.findByCreditGuaranteeIdInstituteCodeOrderByCreditGuaranteeIdYearAscCreditGuaranteeIdMonthAsc(anyString())).thenReturn(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))));
        when(instituteRepository.findByInstituteName(anyString())).thenReturn(null);
        when(creditGuaranteePredicate.getPredicateAmount(anyInt(), any())).thenReturn(Integer.valueOf(0));

        Integer result = creditGuaranteeService.getCreditGuaranteePredicate("instituteName", Integer.valueOf(0));
        Assert.assertEquals(Integer.valueOf(0), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme