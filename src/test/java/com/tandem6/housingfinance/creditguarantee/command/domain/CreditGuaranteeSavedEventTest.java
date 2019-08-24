package com.tandem6.housingfinance.creditguarantee.command.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CreditGuaranteeSavedEventTest {
    @Mock
    CreditGuarantee creditGuarantee;
    @Mock
    Object source;
    @InjectMocks
    CreditGuaranteeSavedEvent creditGuaranteeSavedEvent;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEquals() throws Exception {
        boolean result = creditGuaranteeSavedEvent.equals("o");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCanEqual() throws Exception {
        boolean result = creditGuaranteeSavedEvent.canEqual("other");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testHashCode() throws Exception {
        int result = creditGuaranteeSavedEvent.hashCode();
        Assert.assertEquals(0, result);
    }

    @Test
    public void testToString() throws Exception {
        String result = creditGuaranteeSavedEvent.toString();
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme