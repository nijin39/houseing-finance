package com.tandem6.housingfinance.institute.application;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteException;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstituteServiceTest {
    @MockBean private InstituteRepository instituteRepository;
    @Autowired private InstituteService instituteService;

    @Test
    public void T01_모든_기관명_정상가져오기() throws Exception {
        //Given
        List<Institute> expected = Arrays.asList(
                new Institute("bnk0001","기업은행"),
                new Institute("bnk0002","우리은행")
        );
        when(instituteRepository.findAll()).thenReturn(expected);

        //When
        List<Institute> allInstitute = instituteService.findAllInstitute();

        //Than
        Assertions.assertThat( allInstitute ).isEqualTo(expected);
    }

    @Test(expected = InstituteException.class)
    public void T02_이미_있는_기관등록시_예외발생() throws Exception {

        //Given
        Institute expected = new Institute("bnk0001","기업은행");
        when(instituteRepository.findByInstituteName(anyString())).thenReturn(Optional.of(expected));

        //when
        Institute institute = instituteService.AddInstitute("기업은행");
    }

    @Test
    public void T03_정상적으로_기관등록() throws Exception {

        //Given
        Institute expected = new Institute("bnk0001","기업은행");

        when(instituteRepository.findByInstituteName(anyString())).thenReturn(Optional.empty());
        when(instituteRepository.save(new Institute("기업은행"))).thenReturn(expected);

        //when
        Institute institute = instituteService.AddInstitute("기업은행");

        //than
        Assertions.assertThat(institute).isEqualTo(expected);
    }

    @Test
    public void T04_리스트로_목록이_들어오면_미등록_기관만_등록() throws Exception {
        //Given
        List<String> instituteList = Arrays.asList("기업은행", "기업은행");
        Institute institute = new Institute("bnk0001", "기업은행");
        when(instituteRepository.findByInstituteName(anyString())).thenReturn(Optional.empty(), Optional.of(institute));

        //When
        instituteService.AddInstituteList(instituteList);

        //then
        verify(instituteRepository, times(1)).save(new Institute("기업은행"));
    };
}

