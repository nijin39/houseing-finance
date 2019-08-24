package com.tandem6.housingfinance.admin.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.institute.application.InstituteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class AdminControllerTest {
    @Mock
    InstituteService instituteService;
    @Mock
    CreditGuaranteeService creditGuaranteeService;
    @Mock
    Logger log;
    @InjectMocks
    AdminController adminController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpload() throws Exception {
        ResponseEntity<?> result = adminController.upload(null);
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme