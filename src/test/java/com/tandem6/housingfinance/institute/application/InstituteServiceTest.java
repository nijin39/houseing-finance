package com.tandem6.housingfinance.institute.application;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteException;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InstituteServiceTest {
    @Autowired InstituteService instituteService;

    @Test
    public void testFindAllInstitute() throws Exception {

    }

    @Test
    public void testAddInstitute() throws Exception {

        instituteService.AddInstitute("기업은행");
        List<Institute> instituteList = instituteService.findAllInstitute();
        Assertions.assertThat(instituteList).hasSize(1);

    }

    @Test(expected = InstituteException.class)
    public void exception() throws Exception {
        instituteService.AddInstitute("기업은행");
        instituteService.AddInstitute("기업은행");
    }
}

