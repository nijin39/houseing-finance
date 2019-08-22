package com.tandem6.housingfinance.creditguarantee.command.application;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteePredicate;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeRepository;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuarantee;
import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeId;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class CreditGuaranteeService {

    public static final String YEAR = "연도";
    public static final String MONTH = "월";


    final private CreditGuaranteeRepository creditGuaranteeRepository;
    final private InstituteRepository instituteRepository;
    final private CreditGuaranteePredicate creditGuaranteePredicate;

    public CreditGuaranteeService(CreditGuaranteeRepository creditGuaranteeRepository, InstituteRepository instituteRepository, CreditGuaranteePredicate creditGuaranteePredicate) {
        this.creditGuaranteeRepository = creditGuaranteeRepository;
        this.instituteRepository = instituteRepository;
        this.creditGuaranteePredicate = creditGuaranteePredicate;
    }

    @Transactional
    public List<CreditGuarantee> findAllCreditGuarantees() {
        return creditGuaranteeRepository.findAll();
    }

    @Transactional
    public void importCsv(Stream<Map<String, String>> csvData) {
        csvData.forEach(this::generate);
    }

    public Integer getCreditGuaranteePredicate(String instituteName, Integer month) throws Exception {
        String instituteCode = null;

        Optional<Institute> instituteOptional = instituteRepository.findByInstituteName(instituteName);
        if( instituteOptional.isPresent()){
           instituteCode = instituteOptional.get().getInstituteCode();
        } else {
            throw new Exception("없다. 코드");
        }

        //TODO 변수명 변경
        List<CreditGuarantee> baseData = creditGuaranteeRepository.findByCreditGuaranteeIdInstituteCodeOrderByCreditGuaranteeIdYearAscCreditGuaranteeIdMonthAsc(instituteCode);
        List<Long> data = baseData.stream().map(CreditGuarantee::getAmount).collect(Collectors.toList());

        return creditGuaranteePredicate.getPredicateAmount(month, data);
    }

    private void generate(Map<String, String> data) {
        String year = data.get(YEAR);
        String month = data.get(MONTH);
        data.forEach((instituteName, amount) -> {
            if (!instituteName.equals(YEAR) && !instituteName.equals(MONTH)) {
                Optional<Institute> instituteCode = instituteRepository.findByInstituteName(instituteName);
                instituteCode.ifPresent(institute -> {
                    CreditGuaranteeId creditGuaranteeId = new CreditGuaranteeId(institute.getInstituteCode(), year, Integer.valueOf(month));
                    CreditGuarantee creditGuarantee = new CreditGuarantee(creditGuaranteeId, Long.valueOf(amount.replaceAll("[^\\d.]", "")));
                    creditGuaranteeRepository.save(creditGuarantee);
                });
            }
        });
    }
}
