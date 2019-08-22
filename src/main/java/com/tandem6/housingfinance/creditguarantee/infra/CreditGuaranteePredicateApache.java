package com.tandem6.housingfinance.creditguarantee.infra;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteePredicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CreditGuaranteePredicateApache implements CreditGuaranteePredicate {


    @Override
    public Integer getPredicateAmount(Integer month, List<Long> data) {

        data.stream().forEach(dataItem -> log.info(":::: {}", dataItem));

        SimpleRegression simpleRegression = new SimpleRegression();

        for(int i = 0; i < data.size(); i++) {
            simpleRegression.addData(i, data.get(i));
        }

        System.out.println("Start date unit at t = 0:");
        System.out.println("Intercept: " + simpleRegression.getIntercept());
        System.out.println("Slope    : " + simpleRegression.getSlope());

        double predict = simpleRegression.predict(data.size() + month);

        return (int)predict;
    }
}
