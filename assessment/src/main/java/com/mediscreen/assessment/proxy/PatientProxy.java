package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "patient", url = "${mediscreen.patientUrl}")
public interface PatientProxy {

    @GetMapping("/patient/id")
    public PatientDto getPatientById(@RequestParam Long id);

    @GetMapping("patient/lastName")
    public List<PatientDto> getPatientByFamilyName(@RequestParam String lastName);

}
