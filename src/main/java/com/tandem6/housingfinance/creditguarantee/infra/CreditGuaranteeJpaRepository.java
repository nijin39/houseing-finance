package com.tandem6.housingfinance.creditguarantee.infra;

import com.tandem6.housingfinance.creditguarantee.domain.CreditCuaranteeRepository;
import com.tandem6.housingfinance.creditguarantee.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.domain.CreditGuaranteeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditGuaranteeJpaRepository extends JpaRepository<CreditGuarantee, CreditGuaranteeId>, CreditCuaranteeRepository {
}
