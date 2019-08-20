package com.tandem6.housingfinance.creditguarantee.domain;

import java.util.List;

public interface CreditCuaranteeRepository {
    CreditGuarantee save(CreditGuarantee creditGuarantee);
    List<CreditGuarantee> findAll();
}
