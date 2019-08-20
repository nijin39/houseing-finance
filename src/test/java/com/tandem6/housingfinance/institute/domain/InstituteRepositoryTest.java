package com.tandem6.housingfinance.institute.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InstituteRepositoryTest {

    @Autowired
    private InstituteRepository instituteRepository;

    @Test
    public void T01_금융기관_정상_추가_단건() {
        Institute institute = new Institute("기업은행");
        instituteRepository.save(institute);

        List<Institute> instituteList = instituteRepository.findAll();
        log.info(institute.toString());

        Assertions.assertThat(instituteList).isNotEmpty();
    }

    @Test
    public void T02_금융기관_정상_추가건_여러건() {
        Institute institute1 = new Institute("기업은행");
        Institute institute2 = new Institute("국민은행");
        instituteRepository.save(institute1);
        instituteRepository.save(institute2);

        List<Institute> instituteList = instituteRepository.findAll();
        log.info(instituteList.toString());

        Assertions.assertThat(instituteList).isNotEmpty();
    }

    @Test
    public void T03_금융기관_이름으로_조회() {
        Institute institute = new Institute("기업은행");
        instituteRepository.save(institute);

        Optional<Institute> foundInstitute = instituteRepository.findByInstituteName("기업은행");

        foundInstitute.ifPresent(institute1 -> { Assertions.assertThat(institute1.getInstituteName()).isEqualTo("기업은행");} );
    }
}