package com.tandem6.housingfinance.creditguaranteesummary.infra;

import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummary;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryId;
import com.tandem6.housingfinance.creditguaranteesummary.domain.CreditGuaranteeSummaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditGuaranteeSummaryJpaRepository extends JpaRepository<CreditGuaranteeSummary, CreditGuaranteeSummaryId>, CreditGuaranteeSummaryRepository {
    CreditGuaranteeSummary save(CreditGuaranteeSummary creditGuaranteeSummary);
}
