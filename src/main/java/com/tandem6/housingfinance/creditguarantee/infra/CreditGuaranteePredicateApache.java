package com.tandem6.housingfinance.creditguarantee.infra;

import com.tandem6.housingfinance.creditguarantee.command.domain.CreditGuaranteePredicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CreditGuaranteePredicateApache implements CreditGuaranteePredicate {

    //달이 10월까지 밖에 없기 때문에 2를 더 더함.
    public static final int correctLength = 2;

    @Override
    public Integer getPredicateAmount(Integer month, List<Long> data) {

        data.stream().forEach(dataItem -> log.info(":::: {}", dataItem));

        SimpleRegression simpleRegression = new SimpleRegression();

        for(int i = 0; i < data.size(); i++) {
            simpleRegression.addData(i, data.get(i));
        }

        double predict = simpleRegression.predict(data.size()+ correctLength + month);

        return (int)predict;
    }
}
