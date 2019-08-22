package com.tandem6.housingfinance.creditguaranteesummary.domain;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeSavedEvent;
import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Component
public class CreditGuaranteeSummaryListener {

    final private CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository;
    final private InstituteRepository instituteRepository;

    public CreditGuaranteeSummaryListener(CreditGuaranteeSummaryRepository creditGuaranteeSummaryRepository, InstituteRepository instituteRepository) {
        this.creditGuaranteeSummaryRepository = creditGuaranteeSummaryRepository;
        this.instituteRepository = instituteRepository;
    }

    //@Async TODO Async는 좀더 있다가.
    @EventListener
    public void creditGuaranteeSavedEventHandler(CreditGuaranteeSavedEvent event) {
        String instituteCode = event.getCreditGuarantee().getCreditGuaranteeId().getInstituteCode();
        Optional<Institute> instituteOptional = instituteRepository.findByInstituteCode(instituteCode);
        if( instituteOptional.isPresent() ) {
            Institute institute = instituteOptional.get();
            CreditGuaranteeSummaryId creditGuaranteeSummaryId = new CreditGuaranteeSummaryId(
                    institute.getInstituteName(),
                    event.getCreditGuarantee().getCreditGuaranteeId().getYear());

            Optional<CreditGuaranteeSummary> creditGuaranteeSummaryOptional = creditGuaranteeSummaryRepository.findByCreditGuaranteeSummaryId(creditGuaranteeSummaryId);
            if( creditGuaranteeSummaryOptional.isPresent() ){
                Long addedAmount = creditGuaranteeSummaryOptional.get().getAmount() + event.getCreditGuarantee().getAmount();
                creditGuaranteeSummaryRepository.save(new CreditGuaranteeSummary(creditGuaranteeSummaryId, addedAmount));
            } else {
                creditGuaranteeSummaryRepository.save(new CreditGuaranteeSummary(creditGuaranteeSummaryId, event.getCreditGuarantee().getAmount()));
            }

        }
    }
}