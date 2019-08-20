package com.tandem6.housingfinance.institute.domain;

import java.util.List;
import java.util.Optional;

public interface InstituteRepository {
    List<Institute> findAll();
    Optional<Institute> findByInstituteName(String instituteName);
    Institute save(Institute institute);
}
