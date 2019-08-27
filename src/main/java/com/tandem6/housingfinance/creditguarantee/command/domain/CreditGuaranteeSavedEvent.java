package com.tandem6.housingfinance.creditguarantee.command.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreditGuaranteeSavedEvent extends ApplicationEvent {
    private final CreditGuarantee creditGuarantee;

    public CreditGuaranteeSavedEvent(Object source) {
        super(source);
        this.creditGuarantee = (CreditGuarantee) source;
    }
}
