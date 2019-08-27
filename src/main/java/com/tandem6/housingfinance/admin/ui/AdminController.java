package com.tandem6.housingfinance.admin.ui;

import com.tandem6.housingfinance.creditguarantee.command.application.CreditGuaranteeService;
import com.tandem6.housingfinance.institute.application.InstituteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class AdminController {

    final private InstituteService instituteService;
    final private CreditGuaranteeService creditGuaranteeService;

    public AdminController(InstituteService instituteService, CreditGuaranteeService creditGuaranteeService) {
        this.instituteService = instituteService;
        this.creditGuaranteeService = creditGuaranteeService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            return ResponseEntity.badRequest().body("유효하지 않은 파일입니다.");
        }

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 3);
        if (!extension.toLowerCase().equals("csv")) {
            return ResponseEntity.badRequest().body("csv 파일만 지원합니다.");
        }

        CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(new InputStreamReader(file.getInputStream()));

        //01. 헤더 DB저장
        List<String> csvHeader = records.getHeaderMap().keySet().stream().skip(2).collect(Collectors.toList());
        instituteService.AddInstituteList(csvHeader);

        //02. 내용 DB 저장
        Stream<Map<String, String>> csvData = StreamSupport.stream(records.spliterator(), false)
                .map(record -> record.toMap());
        creditGuaranteeService.importCsv(csvData);

        return ResponseEntity.ok("Uploaded Finished");
    }

}
