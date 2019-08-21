package com.tandem6.housingfinance.institute.application;

import com.tandem6.housingfinance.institute.domain.Institute;
import com.tandem6.housingfinance.institute.domain.InstituteException;
import com.tandem6.housingfinance.institute.domain.InstituteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class InstituteService {

    final private InstituteRepository instituteRepository;
    final private CacheManager cacheManager;

    public InstituteService(InstituteRepository instituteRepository, CacheManager cacheManager) {
        this.instituteRepository = instituteRepository;
        this.cacheManager = cacheManager;
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

        cacheManager.getCache("institute").clear();
    }

    public void AddInstituteList(List<String> instituteNameList){
        instituteNameList.stream()
                .forEach(instituteName -> {
                    try {
                        this.AddInstitute(instituteName);
                    } catch (InstituteException e) {
                        log.info("이미 등록 하였으므로 다시 등록하지 않습니다.");
                    }
                });
    }
}
