package com.tandem6.housingfinance.creditguarantee.command.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class CreditGuaranteeTest {
    @Mock
    CreditGuaranteeId creditGuaranteeId;
    //Field createdDate of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field modifiedDate of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    CreditGuarantee creditGuarantee;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDomainEvents() throws Exception {
        Collection<Object> result = creditGuarantee.domainEvents();
        Assert.assertEquals(Arrays.<Object>asList("replaceMeWithExpectedResult"), result);
    }

    @Test
    public void testCallbackMethod() throws Exception {
        creditGuarantee.callbackMethod();
    }

    @Test
    public void testSetCreditGuaranteeId() throws Exception {
        creditGuarantee.setCreditGuaranteeId(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)));
    }

    @Test
    public void testSetAmount() throws Exception {
        creditGuarantee.setAmount(Long.valueOf(1));
    }

    @Test
    public void testToString() throws Exception {
        String result = creditGuarantee.toString();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testEquals() throws Exception {
        boolean result = creditGuarantee.equals("o");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCanEqual() throws Exception {
        boolean result = creditGuarantee.canEqual("other");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testHashCode() throws Exception {
        int result = creditGuarantee.hashCode();
        Assert.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme