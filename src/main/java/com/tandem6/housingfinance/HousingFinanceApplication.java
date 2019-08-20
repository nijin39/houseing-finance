package com.tandem6.housingfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HousingFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HousingFinanceApplication.class, args);
    }

}
