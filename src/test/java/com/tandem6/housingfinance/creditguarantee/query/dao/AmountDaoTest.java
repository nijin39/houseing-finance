package com.tandem6.housingfinance.creditguarantee.query.dao;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAmountInstitute;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinAverageByInstituteDto;
import com.tandem6.housingfinance.creditguarantee.query.dto.MaxAndMinYearDto;
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

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = true)
public class AmountDaoTest {

    @Autowired InstituteRepository instituteRepository;
    @Autowired CreditGuaranteeRepository creditGuaranteeRepository;
    @Autowired EntityManager entityManager;
    @Autowired AmountDao amountDao;

    @Before
    public void setUp() {
        instituteRepository.save(new Institute("bnk0001", "기업은행"));

        CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId("bnk0001","2017", 12);
        CreditGuarantee creditGuarantee = new CreditGuarantee(creditGuaranteeId, 100L);
        creditGuaranteeRepository.save(creditGuarantee);
        CreditGuaranteeId creditGuaranteeId1 = new CreditGuaranteeId("bnk0001","2018", 12);
        CreditGuarantee creditGuarantee1 = new CreditGuarantee(creditGuaranteeId1, 200L);
        creditGuaranteeRepository.save(creditGuarantee1);
    }

    @Test
    public void T01_정상적으로_최소_최대_Year값_리턴() throws Exception {
        List<MaxAndMinYearDto> result = amountDao.getMaxAndMinYear();
        Assertions.assertThat( result.get(0).getMinYear()).isEqualTo("2017");
        Assertions.assertThat( result.get(0).getMaxYear()).isEqualTo("2018");
    }

    @Test
    public void T02_최대_최소_지원해_평균_리턴() throws Exception {
        MaxAndMinAverageByInstituteDto result = amountDao.getMaxAndMinAverage("기업은행");
        Assertions.assertThat( result.getSupport_amount().get(0).getAmount() ).isEqualTo(17);
        Assertions.assertThat( result.getSupport_amount().get(1).getAmount() ).isEqualTo(8);
    }

    @Test
    public void T03_최대지원_금융기관_리턴() throws Exception {
        MaxAmountInstitute result = amountDao.getMaxAmountInstitute();
        Assertions.assertThat( result.getBank() ).isEqualTo("기업은행");
        Assertions.assertThat( result.getYear() ).isEqualTo(2018);
    }

    @Test
    public void T04_정상적인_연간리포트_출력() throws Exception {
        AmountAnnualReport result = amountDao.generateAmountAnnualReport();
        Assertions.assertThat( result.getAnnualReportList() ).hasSize(2);
    }
}

