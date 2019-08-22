package com.tandem6.housingfinance.institute.domain;

import io.swagger.models.auth.In;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface InstituteRepository {
    List<Institute> findAll();

    @Cacheable("instituteByName")
    Optional<Institute> findByInstituteName(String instituteName);
    @Cacheable("instituteByCode")
    Optional<Institute> findByInstituteCode(String instituteCode);
    Institute save(Institute institute);
}
