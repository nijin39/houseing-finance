package com.tandem6.housingfinance.institute.application;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteService {

    final private InstituteRepository instituteRepository;

    public InstituteService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public List<Institute> findAllInstitute(){
        return instituteRepository.findAll();
    }
}
