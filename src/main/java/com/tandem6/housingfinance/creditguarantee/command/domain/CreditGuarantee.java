package com.tandem6.housingfinance.creditguarantee.command.domain;

import com.tandem6.housingfinance.common.domain.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class CreditGuarantee extends TimeEntity {

    @EmbeddedId
    private CreditGuaranteeId creditGuaranteeId;

    @Column
    private Long amount;

    @DomainEvents
    Collection<Object> domainEvents() {
        List<Object> result = new ArrayList<Object>();
        result.add(new CreditGuaranteeSavedEvent(this));
        return result;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        //System.out.println("DATA SAVED!\n"+"WELL DONE");
    }
}
