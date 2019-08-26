package com.tandem6.housingfinance.creditguarantee.ui;

import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.common.config.JwtAuthenticationEntryPoint;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
import com.tandem6.housingfinance.creditguarantee.query.dto.*;
import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.ui.InstituteRestController;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value= CreditGuaranteeRestController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CreditGuaranteeRestControllerTest {
    @Autowired private MockMvc mvc;

    @MockBean private JwtTokenUtil jwtTokenUtil;
    @MockBean private JwtUserDetailsService jwtUserDetailsService;
    @MockBean private InstituteService instituteService;
    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean AmountReportService amountReportService;
    @MockBean CreditGuaranteeService creditGuaranteeService;
    @Autowired CreditGuaranteeRestController creditGuaranteeRestController;

    @Test
    @WithMockUser(username="spring")
    public void T01_Credit_Guarantee_빈값일_때() throws Exception {

        List emptyList = Collections.EMPTY_LIST;
        when(creditGuaranteeService.findAllCreditGuarantees()).thenReturn(emptyList);

        //When
        ResultActions actions = mvc
                .perform(get("/api/creditGuarantees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="spring")
    public void T02_Credit_Guarantee_정상적으로_존재할_때() throws Exception {
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 12);
        CreditGuarantee creditGuarantee = new CreditGuarantee(creditGuaranteeId, 100L);
        CreditGuaranteeId creditGuaranteeId1 = new CreditGuaranteeId("bnk0001","2018", 12);
        CreditGuarantee creditGuarantee1 = new CreditGuarantee(creditGuaranteeId1, 200L);
        List<CreditGuarantee> creditGuaranteeList = Arrays.asList(creditGuarantee, creditGuarantee1);
        when(creditGuaranteeService.findAllCreditGuarantees()).thenReturn(creditGuaranteeList);

        //When
        ResultActions actions = mvc
                .perform(get("/api/creditGuarantees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    @WithMockUser(username="spring")
    public void T03_MaxAndMinYear가_존재할_때() throws Exception {
        //Given
        when(amountReportService.getMaxAndMinYear()).thenReturn(Collections.EMPTY_LIST);

        //When /creditGuarantee/years
        //When
        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/years")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        //Then
        actions.andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser(username="spring")
    public void T04_MaxAndMinYear가_존재할_때() throws Exception {
        //Given
        MaxAndMinYearDto maxAndMinYearDto = new MaxAndMinYearDto("2017", "2019");
        when(amountReportService.getMaxAndMinYear()).thenReturn(Arrays.asList(maxAndMinYearDto));

        //When /creditGuarantee/years
        //When
        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/years")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        //Then

    }

    @Test
    @WithMockUser(username="spring")
    public void T05_금융기관에_대한_max_amount가_미_존재할_때() throws Exception {

    }

    @Test
    @WithMockUser(username="spring")
    public void T06_금융기관에_대한_max_amount가_존재할_때() throws Exception {
        when(amountReportService.getMaxAmountInstitute()).thenReturn(new MaxAmountInstitute(Integer.valueOf(0), "bank"));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/institute/max-amount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isOk());
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