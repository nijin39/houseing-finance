package com.tandem6.housingfinance.creditguarantee.domain;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCuaranteeRepositoryTest {

    @Autowired CreditCuaranteeRepository creditCuaranteeRepository;
    @Autowired InstituteRepository instituteRepository;
    private Institute institute;

    @Before
    public void setup() {
        institute = instituteRepository.save(new Institute("기업은행"));
    }

    @Test
    public void T01_CreditGuarantee_생성하기(){
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), "2019", "01");
        CreditGuarantee creditGuarantee = creditCuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId, 2000l));

        log.info("Credit Guarantee ::: {}", creditGuarantee.toString());
    }

    @Test
    public void T02_CreditGuarantee_전체조회(){
        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), "2019", "01");
        CreditGuarantee creditGuarantee = creditCuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId, 2000l));

        CreditGuaranteeId creditGuaranteeId2 = new CreditGuaranteeId(institute.getInstituteCode(), "2019", "02");
        CreditGuarantee creditGuarantee2 = creditCuaranteeRepository.save(new CreditGuarantee(creditGuaranteeId2, 4000l));


        Assertions.assertThat( creditCuaranteeRepository.findAll() ).hasSize(2);
    }
}

