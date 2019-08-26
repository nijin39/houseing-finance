package com.tandem6.housingfinance.creditguarantee.command.domain;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditGuaranteeRepositoryTest {

    @Autowired CreditGuaranteeRepository creditGuaranteeRepository;
    @Autowired InstituteRepository instituteRepository;
    private Institute institute;

    @Before
    public void setup() {
        institute = instituteRepository.save(new Institute("기업은행"));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void T01_CreditGuarantee_생성하기(){
        //Given
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), "2019", 1);

        //When
        CreditGuarantee creditGuarantee = creditGuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId, 2000l));

        //Than
        Assertions.assertThat( creditGuaranteeRepository.findAll() ).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void T02_CreditGuarantee_전체조회(){
        //Given
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), "2019", 1);
        CreditGuaranteeId creditGuaranteeId2 = new CreditGuaranteeId(institute.getInstituteCode(), "2019", 2);
        CreditGuarantee creditGuarantee = creditGuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId, 2000l));
        CreditGuarantee creditGuarantee2 = creditGuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId2, 4000l));

        // When, Then
        Assertions.assertThat( creditGuaranteeRepository.findAll() ).hasSize(2);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void T03_CreditGuarantee_정렬(){
        //Given
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), "2019", 1);
        CreditGuaranteeId creditGuaranteeId2 = new CreditGuaranteeId(institute.getInstituteCode(), "2019", 2);
        CreditGuarantee creditGuarantee = creditGuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId2, 2000l));
        CreditGuarantee creditGuarantee2 = creditGuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId, 4000l));

        // When
        List<CreditGuarantee> creditGuaranteeList = creditGuaranteeRepository.findByCreditGuaranteeIdInstituteCodeOrderByCreditGuaranteeIdYearAscCreditGuaranteeIdMonthAsc(institute.getInstituteCode());

        // Then
        Assertions.assertThat(creditGuaranteeList.get(0).getCreditGuaranteeId().getMonth()).isEqualTo(1);
        Assertions.assertThat(creditGuaranteeList.get(1).getCreditGuaranteeId().getMonth()).isEqualTo(2);
    }
}

