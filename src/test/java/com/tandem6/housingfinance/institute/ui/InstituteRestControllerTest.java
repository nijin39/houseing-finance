package com.tandem6.housingfinance.institute.ui;

import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.common.config.JwtAuthenticationEntryPoint;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value=InstituteRestController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class InstituteRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean private JwtTokenUtil jwtTokenUtil;
    @MockBean private JwtUserDetailsService jwtUserDetailsService;
    @MockBean private InstituteService instituteService;
    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private InstituteRestController instituteRestController;

    @Test
    @WithMockUser(username="spring")
    public void T01_금융기관_리스트가_없으면_204() throws Exception {

        //Given
        List<Institute> expected = Arrays.asList(
                new Institute("bnk0001","기업은행"),
                new Institute("bnk0002","우리은행")
        );
        when(instituteService.findAllInstitute()).thenReturn(expected);

        //When
        ResultActions actions = mvc
                .perform(get("/api/institutes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    @WithMockUser(username="spring")
    public void T02_정상적으로_금융기관_리스트_출력() throws Exception {

        //Given
        List<Institute> expected = Arrays.asList(
                new Institute("bnk0001","기업은행"),
                new Institute("bnk0002","우리은행")
        );
        when(instituteService.findAllInstitute()).thenReturn(expected);

        //When
        ResultActions actions = mvc
                .perform(get("/api/institutes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //Then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
     }
}
