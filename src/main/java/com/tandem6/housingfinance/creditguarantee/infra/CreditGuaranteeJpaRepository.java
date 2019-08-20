package com.tandem6.housingfinance.creditguarantee.infra;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditGuaranteeJpaRepository extends JpaRepository<CreditGuarantee, CreditGuaranteeId>, CreditGuaranteeRepository {
}
