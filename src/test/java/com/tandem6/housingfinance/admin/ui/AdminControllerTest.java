package com.tandem6.housingfinance.admin.ui;

import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.common.config.JwtAuthenticationEntryPoint;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.institute.application.InstituteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value= AdminController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AdminControllerTest {
    @Autowired private MockMvc mvc;

    @MockBean private JwtTokenUtil jwtTokenUtil;
    @MockBean private JwtUserDetailsService jwtUserDetailsService;
    @MockBean private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean private InstituteService instituteService;
    @MockBean private CreditGuaranteeService creditGuaranteeService;
    @Autowired private AdminController adminController;

    @WithMockUser(username="spring")
    @Test
    public void T01_확장자가_csv가_아닐_때() throws Exception {
        MockMultipartFile secondFile = new MockMultipartFile("file", "other-file-name.data", "text/plain", "some other type".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                .file(secondFile))
                .andExpect(status().is(400));
    }

    @WithMockUser(username="spring")
    @Test
    public void T02_파일_내용이_없을_때() throws Exception {
        MockMultipartFile secondFile = new MockMultipartFile("file", "other-file-name.data", "text/plain", "".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                .file(secondFile))
                .andExpect(status().is(400));
    }

    @WithMockUser(username="spring")
    @Test
    public void T03_파일이_정상일_때() throws Exception {
        MockMultipartFile secondFile = new MockMultipartFile("file", "other-file-name.csv", "text/plain", "some other type".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                .file(secondFile))
                .andExpect(status().is(200))
                .andExpect(content().string("Uploaded Finished"));
    }
}

