package com.tandem6.housingfinance.creditguarantee.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.common.config.JwtAuthenticationEntryPoint;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportException;
import com.tandem6.housingfinance.creditguarantee.query.application.AmountReportService;
import com.tandem6.housingfinance.creditguarantee.query.dao.AmountDaoException;
import com.tandem6.housingfinance.creditguarantee.query.dto.*;
import com.tandem6.housingfinance.creditguarantee.ui.dto.PredicateRequest;
import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @MockBean InstituteRepository instituteRepository;
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
        when(amountReportService.getMaxAmountInstitute()).thenThrow(new AmountReportException("TEST", 1L));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/institute/max-amount")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isNoContent());
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
    @WithMockUser(username="spring")
    public void T07_금융기관에_대한_Max_Min_평균값_미존재() throws Exception {
        when(amountReportService.getMaxAndMinAverage(anyString())).thenThrow(new AmountReportException("test",2L));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/institute/abc/max-min-average")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username="spring")
    public void T08_금융기관에_대한_Max_Min_평균값_존재() throws Exception {
        when(amountReportService.getMaxAndMinAverage(anyString())).thenReturn(new MaxAndMinAverageByInstituteDto("abc", Arrays.<AmountByYear>asList(new AmountByYear(Integer.valueOf(0), Integer.valueOf(0)))));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/institute/abc/max-min-average")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="spring")
    public void T09_예측값에_대한_정상적인_값이_존재하지_않을_때() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate("기업은행", 3)).thenThrow(new Exception());
        PredicateRequest predicateRequest = new PredicateRequest("기업은행",3);

        ResultActions actions = mvc
                .perform(post("/api/creditGuarantee/predicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(predicateRequest)))
                .andDo(print());

        actions.andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(username="spring")
    public void T10_예측값에_대한_정상적인_값이_존재할_때() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate(anyString(), anyInt())).thenReturn(Integer.valueOf(0));
        when(instituteRepository.findByInstituteName("기업은행")).thenReturn(Optional.of(new Institute("기업은행", "bnk0001")));
        PredicateRequest predicateRequest = new PredicateRequest("기업은행",3);

        ResultActions actions = mvc
                .perform(post("/api/creditGuarantee/predicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(predicateRequest)))
                .andDo(print());

        actions.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="spring")
    public void T11_예측요청의_범위에_오류가_있을_때() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate(anyString(), anyInt())).thenReturn(Integer.valueOf(0));
        when(instituteRepository.findByInstituteName("기업은행")).thenReturn(Optional.empty());
        PredicateRequest predicateRequest = new PredicateRequest("기업은행",15);

        ResultActions actions = mvc
                .perform(post("/api/creditGuarantee/predicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(predicateRequest)))
                .andDo(print());

        actions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username="spring")
    public void T12_예측요청에_대한_금융기관_입력이_Null인_경우() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate(anyString(), anyInt())).thenReturn(Integer.valueOf(0));
        when(instituteRepository.findByInstituteName("기업은행")).thenReturn(Optional.empty());
        PredicateRequest predicateRequest = new PredicateRequest(null,3);

        ResultActions actions = mvc
                .perform(post("/api/creditGuarantee/predicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(predicateRequest)))
                .andDo(print());

        actions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username="spring")
    public void T13_예측요청에_대한_금융기관이_없을_때() throws Exception {
        when(creditGuaranteeService.getCreditGuaranteePredicate(anyString(), anyInt())).thenReturn(Integer.valueOf(0));
        when(instituteRepository.findByInstituteName("기업은행")).thenReturn(Optional.empty());
        PredicateRequest predicateRequest = new PredicateRequest("기업은행",3);

        ResultActions actions = mvc
                .perform(post("/api/creditGuarantee/predicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(predicateRequest)))
                .andDo(print());

        actions.andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username="spring")
    public void T14_연간_리포트_비정상_출력() throws Exception {
        when(amountReportService.generateAmountAnnualReport()).thenThrow(new AmountDaoException("test", 1L));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/annualReport")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isInternalServerError());
    }


    @Test
    @WithMockUser(username="spring")
    public void T15_연간_리포트_정상_출력() throws Exception {
        when(amountReportService.generateAmountAnnualReport()).thenReturn(new AmountAnnualReport(Arrays.<AnnualReport>asList(new AnnualReport(Integer.valueOf(0), Integer.valueOf(0), new HashMap<String, Integer>() {{
            put("String", Integer.valueOf(0));
        }}))));

        ResultActions actions = mvc
                .perform(get("/api/creditGuarantee/annualReport")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        actions.andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}