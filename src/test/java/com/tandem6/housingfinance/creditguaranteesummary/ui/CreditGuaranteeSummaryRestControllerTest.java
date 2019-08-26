package com.tandem6.housingfinance.creditguaranteesummary.ui;

import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.common.config.JwtAuthenticationEntryPoint;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryExcpetion;
import com.tandem6.housingfinance.creditguaranteesummary.application.CreditGuaranteeSummaryService;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryId;
import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.ui.InstituteRestController;
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
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value= CreditGuaranteeSummaryRestController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CreditGuaranteeSummaryRestControllerTest {
    @MockBean
    CreditGuaranteeSummaryService creditGuaranteeSummaryService;

    @Autowired
    private MockMvc mvc;

    @MockBean private JwtTokenUtil jwtTokenUtil;
    @MockBean private JwtUserDetailsService jwtUserDetailsService;
    @MockBean private InstituteService instituteService;
    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    CreditGuaranteeSummaryRestController creditGuaranteeSummaryRestController;

    @Test
    @WithMockUser(username="spring")
    public void T01_최대값이_없으면_204() throws Exception {

        //Given
        CreditGuaranteeSummaryId creditGuaranteeSummaryId = new CreditGuaranteeSummaryId("기업은행", "2017");
        CreditGuaranteeSummary creditGuaranteeSummary = new CreditGuaranteeSummary(creditGuaranteeSummaryId, 100l);

        when(creditGuaranteeSummaryService.findFirstByOrderByAmountDesc()).thenThrow(new CreditGuaranteeSummaryExcpetion("결과가 없습니다.", 1L));

        //When
        ResultActions actions = mvc
                .perform(get("/api/credit-guarantee-summary/max-amount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="spring")
    public void T02_최대값이_있으면_200() throws Exception {

        //Given
        CreditGuaranteeSummaryId creditGuaranteeSummaryId = new CreditGuaranteeSummaryId("기업은행", "2017");
        CreditGuaranteeSummary creditGuaranteeSummary = new CreditGuaranteeSummary(creditGuaranteeSummaryId, 100l);

        when(creditGuaranteeSummaryService.findFirstByOrderByAmountDesc()).thenReturn(creditGuaranteeSummary);

        //When
        ResultActions actions = mvc
                .perform(get("/api/credit-guarantee-summary/max-amount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isOk());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme