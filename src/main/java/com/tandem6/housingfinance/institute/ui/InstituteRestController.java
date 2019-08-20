package com.tandem6.housingfinance.institute.ui;

import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.domain.Institute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/api")
public class InstituteRestController {

    final private InstituteService instituteService;

    public InstituteRestController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping("/institutes")
    public List<Institute> findAllInstitutes(){
        return instituteService.findAllInstitute();
    }
}
