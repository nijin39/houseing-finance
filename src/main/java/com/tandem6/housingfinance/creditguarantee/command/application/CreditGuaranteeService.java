package com.tandem6.housingfinance.creditguarantee.command.application;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Service
public class CreditGuaranteeService {

    public static final String YEAR = "연도";
    public static final String MONTH = "월";


    final private CreditGuaranteeRepository creditGuaranteeRepository;
    final private InstituteRepository instituteRepository;

    public CreditGuaranteeService(CreditGuaranteeRepository creditGuaranteeRepository, InstituteRepository instituteRepository) {
        this.creditGuaranteeRepository = creditGuaranteeRepository;
        this.instituteRepository = instituteRepository;
    }

    @Transactional
    public List<CreditGuarantee> findAllCreditGuarantees() {
        return creditGuaranteeRepository.findAll();
    }

    @Transactional
    public void importCsv(Stream<Map<String, String>> csvData) {
        csvData.forEach(this::generate);
    }

    private void generate(Map<String, String> data) {
        String year = data.get(YEAR);
        String month = data.get(MONTH);
        data.forEach((instituteName, amount) -> {
            if (!instituteName.equals(YEAR) && !instituteName.equals(MONTH)) {
                //TODO Cache 적용 필요
                Optional<Institute> instituteCode = instituteRepository.findByInstituteName(instituteName);
                instituteCode.ifPresent(institute -> {
                    CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), year, month);
                    CreditGuarantee creditGuarantee = new CreditGuarantee(creditGuaranteeId, Long.valueOf(amount.replaceAll("[^\\d.]", "")));
                    creditGuaranteeRepository.save(creditGuarantee);
                });
            }
        });
    }
}
