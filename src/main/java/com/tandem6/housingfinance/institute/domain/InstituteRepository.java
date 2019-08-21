package com.tandem6.housingfinance.institute.domain;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface InstituteRepository {
    List<Institute> findAll();

    @Cacheable("institute")
    Optional<Institute> findByInstituteName(String instituteName);
    Institute save(Institute institute);
}
