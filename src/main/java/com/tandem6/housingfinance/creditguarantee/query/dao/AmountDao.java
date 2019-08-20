package com.tandem6.housingfinance.creditguarantee.query.dao;

import com.tandem6.housingfinance.creditguarantee.query.dto.AmountAnnualReport;
import com.tandem6.housingfinance.creditguarantee.query.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.executor.JpaQueryExecutor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@Component
public class AmountDao {

    final private EntityManager entityManager;

    public AmountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AmountAnnualReport> generateAmountAnnualReport() {
        JpaQueryExecutor queryExecutor = new JpaQueryExecutor();
        List<SampleDto> list = queryExecutor.executeSelect(entityManager, SampleDto.class, "query/sample.sql");
        log.info("::::::::::::::::::: {}",list.toString());
        return null;
    }
}
