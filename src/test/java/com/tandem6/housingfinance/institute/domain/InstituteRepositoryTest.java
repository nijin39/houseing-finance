package com.tandem6.housingfinance.institute.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InstituteRepositoryTest {

    @Autowired
    private InstituteRepository instituteRepository;

    @Test(expected = TransactionSystemException.class)
    public void T01_이름없는_경우_금융기관_저장_예외(){
        Institute institute = new Institute();
        instituteRepository.save(institute);
    }

    @Test
    @Transactional
    @Rollback(value=true)
    public void T02_금융기관_정상_추가_단건() {
        Institute institute = new Institute("기업은행");
        instituteRepository.save(institute);

        List<Institute> instituteList = instituteRepository.findAll();
        log.info(institute.toString());

        Assertions.assertThat(instituteList).isNotEmpty();
    }

    @Test
    public void T03_금융기관_정상_추가건_여러건() {
        Institute institute1 = new Institute("우리은행");
        Institute institute2 = new Institute("국민은행");
        instituteRepository.save(institute1);
        instituteRepository.save(institute2);

        List<Institute> instituteList = instituteRepository.findAll();
        log.info(instituteList.toString());

        Assertions.assertThat(instituteList).hasSize(2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    @Rollback(value=true)
    public void T04_같은_금융기관_여러번_추가_건(){
        Institute institute1 = new Institute("신한은행");
        instituteRepository.save(institute1);
        Institute institute2 = new Institute("신한은행");
        instituteRepository.save(institute2);
    }

    @Test
    @Transactional
    @Rollback(value=true)
    public void T05_금융기관_이름으로_조회() {
        Institute institute = new Institute("기업은행");
        instituteRepository.save(institute);

        Optional<Institute> foundInstitute = instituteRepository.findByInstituteName("기업은행");
        foundInstitute.ifPresent(institute1 -> { Assertions.assertThat(institute1.getInstituteName()).isEqualTo("기업은행");} );
    }
}