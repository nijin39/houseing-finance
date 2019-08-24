package com.tandem6.housingfinance.creditguarantee.query.dao;

import com.tandem6.housingfinance.creditguarantee.query.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class AmountDaoTest {
    @Mock
    EntityManager entityManager;
    @Mock
    Logger log;
    @InjectMocks
    AmountDao amountDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMaxAndMinYear() throws Exception {
        List<MaxAndMinYearDto> result = amountDao.getMaxAndMinYear();
        Assert.assertEquals(Arrays.<MaxAndMinYearDto>asList(new MaxAndMinYearDto("maxYear", "minYear")), result);
    }

    @Test
    public void testGetMaxAndMinAverage() throws Exception {
        MaxAndMinAverageByInstituteDto result = amountDao.getMaxAndMinAverage("instituteName");
        Assert.assertEquals(new MaxAndMinAverageByInstituteDto("instituteName", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))), result);
    }

    @Test
    public void testGetMaxAmountInstitute() throws Exception {
        MaxAmountInstitute result = amountDao.getMaxAmountInstitute();
        Assert.assertEquals(new MaxAmountInstitute(Integer.valueOf(0), "bank"), result);
    }

    @Test
    public void testGenerateAmountAnnualReport() throws Exception {
        AmountAnnualReport result = amountDao.generateAmountAnnualReport();
        Assert.assertEquals(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme