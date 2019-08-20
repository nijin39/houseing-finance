package com.tandem6.housingfinance.common.config;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */

    @Autowired
    private InstituteRepository instituteRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //seedData();
    }

    private void seedData() {
        Institute institute1 = new Institute("기업은행");
        Institute institute2 = new Institute("국민은행");
        instituteRepository.save(institute1);
        instituteRepository.save(institute2);
    }


}