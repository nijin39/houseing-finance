package com.tandem6.housingfinance.institute.application;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteException;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstituteService {

    final private InstituteRepository instituteRepository;

    public InstituteService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public List<Institute> findAllInstitute(){
        return instituteRepository.findAll();
    }

    public void AddInstitute(String instituteName) throws InstituteException {

        if( !instituteRepository.findByInstituteName(instituteName).isPresent() ){
            instituteRepository.save(new Institute(instituteName));
        } else {
            throw new InstituteException("이미 등록된 기관이 있습니다.", 1L);
        }
    }
}
