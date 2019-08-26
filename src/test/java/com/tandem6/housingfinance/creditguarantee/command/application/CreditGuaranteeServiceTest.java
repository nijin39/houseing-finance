package com.tandem6.housingfinance.creditguarantee.command.application;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteePredicate;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CreditGuaranteeService.class})
public class CreditGuaranteeServiceTest {
    @MockBean private CreditGuaranteeRepository creditGuaranteeRepository;
    @MockBean private InstituteRepository instituteRepository;
    @MockBean private CreditGuaranteePredicate creditGuaranteePredicate;

    @Autowired
    CreditGuaranteeService creditGuaranteeService;

    @Test
    public void T01_CreditGuarantee가_존재하지_않는_경우() throws Exception {
        //Given
        List<CreditGuarantee> expected = Collections.emptyList();
        when(creditGuaranteeRepository.findAll()).thenReturn(expected);

        //When
        List<CreditGuarantee> allCreditGuarantees = creditGuaranteeService.findAllCreditGuarantees();

        //Then
        Assertions.assertThat(allCreditGuarantees).hasSize(0);
    }

    @Test
    public void T02_CreditGuarantee가_존재하는_경우() throws Exception {
        //Given
        List<CreditGuarantee> expected = Arrays.asList(
                new CreditGuarantee(new CreditGuaranteeId("bnk0001", "2019", 1), 200L),
                new CreditGuarantee(new CreditGuaranteeId("bnk0001", "2019", 2), 400L)
        );
        when(creditGuaranteeRepository.findAll()).thenReturn(expected);

        //When
        List<CreditGuarantee> allCreditGuarantees = creditGuaranteeService.findAllCreditGuarantees();

        //Then
        Assertions.assertThat(allCreditGuarantees).hasSize(2);
    }

    @Test
    public void T03_연도나_월이_없는_경우(){        //Given
        Map<String, String> map = Stream.of(new String[][] {
                { "하나은행", "abcd" }
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        when(instituteRepository.findByInstituteName("하나은행")).thenReturn(Optional.of(new Institute("bnk0001","하나은행" )));

        Stream<Map<String, String>> builderStream =
                Stream.<Map<String,String>>builder()
                        .add(map)
                        .build();

        //When
        creditGuaranteeService.importCsv(builderStream);

        //Then
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 2);
        verify(creditGuaranteeRepository, times(0)).save( new CreditGuarantee(creditGuaranteeId, 5746L) );
    }

    @Test
    public void T04_Amount가_숫자가_아닌_경우(){
        //Given
        Map<String, String> map = Stream.of(new String[][] {
                { "하나은행", "abcd" },
                { "월", "2" },
                { "연도", "2017" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        when(instituteRepository.findByInstituteName("하나은행")).thenReturn(Optional.of(new Institute("bnk0001","하나은행" )));

        Stream<Map<String, String>> builderStream =
                Stream.<Map<String,String>>builder()
                        .add(map)
                        .build();

        //When
        creditGuaranteeService.importCsv(builderStream);

        //Then
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 2);
        verify(creditGuaranteeRepository, times(0)).save( new CreditGuarantee(creditGuaranteeId, 5746L) );
    }

    @Test
    public void T05_정상적인_DATA가_있는_경우() throws Exception {
        //Given
        Map<String, String> map = Stream.of(new String[][] {
                { "하나은행", "5,746" },
                { "월", "2" },
                { "연도", "2017" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        Map<String, String> map2 = Stream.of(new String[][] {
                { "하나은행", "5,746" },
                { "월", "3" },
                { "연도", "2017" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        when(instituteRepository.findByInstituteName("하나은행")).thenReturn(Optional.of(new Institute("bnk0001","하나은행" )));

        Stream<Map<String, String>> builderStream =
                Stream.<Map<String,String>>builder()
                        .add(map).add(map2)
                        .build();

        //When
        creditGuaranteeService.importCsv(builderStream);

        //Then
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 2);
        verify(creditGuaranteeRepository, times(1)).save( new CreditGuarantee(creditGuaranteeId, 5746L) );
        creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 3);
        verify(creditGuaranteeRepository, times(1)).save( new CreditGuarantee(creditGuaranteeId, 5746L) );
    }

    @Test
    public void T06_정상적으로_예측값이_있는_경우() throws Exception {
        when(creditGuaranteeRepository.findByCreditGuaranteeIdInstituteCodeOrderByCreditGuaranteeIdYearAscCreditGuaranteeIdMonthAsc(anyString())).thenReturn(Arrays.<CreditGuarantee>asList(new CreditGuarantee(new CreditGuaranteeId("instituteCode", "year", Integer.valueOf(0)), Long.valueOf(1))));
        when(instituteRepository.findByInstituteName(anyString())).thenReturn(Optional.of(new Institute("bnk0001","기업은행")));
        when(creditGuaranteePredicate.getPredicateAmount(anyInt(), any())).thenReturn(Integer.valueOf(0));

        Integer result = creditGuaranteeService.getCreditGuaranteePredicate("instituteName", Integer.valueOf(0));
        Assert.assertEquals(Integer.valueOf(0), result);
    }
}
