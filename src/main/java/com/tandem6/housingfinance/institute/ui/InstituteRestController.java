package com.tandem6.housingfinance.institute.ui;

import com.tandem6.housingfinance.institute.application.InstituteService;
import com.tandem6.housingfinance.institute.domain.Institute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
    public ResponseEntity<?> findAllInstitutes(){
        List<Institute> allInstitute = instituteService.findAllInstitute();
        if( allInstitute.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        } else {
            return ResponseEntity.ok(allInstitute);
        }
    }
}
