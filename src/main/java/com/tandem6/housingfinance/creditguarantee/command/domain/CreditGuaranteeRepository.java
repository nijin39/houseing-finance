package com.tandem6.housingfinance.creditguarantee.command.domain;

import java.util.List;

public interface CreditGuaranteeRepository {
    CreditGuarantee save(CreditGuarantee creditGuarantee);
    List<CreditGuarantee> findAll();
}
