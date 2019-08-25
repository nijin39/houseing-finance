package com.tandem6.housingfinance.creditguarantee.command.domain;

import java.util.List;

public interface CreditGuaranteeRepository {
    CreditGuarantee save(CreditGuarantee creditGuarantee);
    List<CreditGuarantee> findAll();

    //Institute Code를 입력으로 Year, Month를 정렬하여 출력
    //TODO 이거라면 아마도 query가 나을 듯함. 바꾸기 바람.
    List<CreditGuarantee> findByCreditGuaranteeIdInstituteCodeOrderByCreditGuaranteeIdYearAscCreditGuaranteeIdMonthAsc(String instituteCode);
}
