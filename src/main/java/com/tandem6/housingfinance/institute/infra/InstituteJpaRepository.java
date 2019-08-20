package com.tandem6.housingfinance.institute.infra;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteJpaRepository extends JpaRepository<Institute, String>, InstituteRepository {
}
