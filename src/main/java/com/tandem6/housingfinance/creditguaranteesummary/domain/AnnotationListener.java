package com.tandem6.housingfinance.creditguaranteesummary.domain;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteeSavedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnotationListener {

    @EventListener
    public void creditGuaranteeSavedEventHandler(CreditGuaranteeSavedEvent event) {
        //log.info("이벤트 발생 시간 : {}", event.getCreditGuarantee().toString());
    }
}