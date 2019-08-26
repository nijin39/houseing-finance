package com.tandem6.housingfinance.creditguaranteesummary.application;

import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryId;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CreditGuaranteeSummaryService.class})
public class CreditGuaranteeSummaryServiceTest {
    @MockBean
    CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository;

    @Autowired
    CreditGuaranteeSummaryService creditGuaranteeSummaryService;

    @Test(expected = CreditGuaranteeSummaryExcpetion.class)
    public void T01_CreditGuaranteeSummary_최대값이_존재하지_않는_경우() throws Exception {

        //Given
        CreditGuaranteeSummaryId creditGuaranteeSummaryId = new CreditGuaranteeSummaryId("기업은행", "2017");
        CreditGuaranteeSummary creditGuaranteeSummary = new CreditGuaranteeSummary(creditGuaranteeSummaryId, 100l);

        when(creditGuaranteeSummaryRepository.findFirstByOrderByAmountDesc()).thenReturn(Optional.empty());

        //When
        CreditGuaranteeSummary result = creditGuaranteeSummaryService.findFirstByOrderByAmountDesc();
    }

    @Test
    public void T02_CreditGuaranteeSummary_최대값이_존재하는_경우() throws Exception {

        //Given
        CreditGuaranteeSummaryId creditGuaranteeSummaryId = new CreditGuaranteeSummaryId("기업은행", "2017");
        CreditGuaranteeSummary creditGuaranteeSummary = new CreditGuaranteeSummary(creditGuaranteeSummaryId, 100l);

        when(creditGuaranteeSummaryRepository.findFirstByOrderByAmountDesc()).thenReturn(Optional.of(creditGuaranteeSummary));

        //When
        CreditGuaranteeSummary result = creditGuaranteeSummaryService.findFirstByOrderByAmountDesc();

        //Then
        Assertions.assertThat(result.getAmount()).isEqualTo(100L);
    }
}