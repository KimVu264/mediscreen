package com.mediscreen.patient.controller;

import com.mediscreen.patient.exception.DataNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin
public class PatientController {

    private static Logger logger = LogManager.getLogger(PatientController.class);

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("show all patients");
        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    @GetMapping("/patient/id")
    public ResponseEntity<Patient> getPatientById(@RequestParam Long id) throws DataNotFoundException {
        logger.info("get patient by id :{}", id);
        return new ResponseEntity<>(patientService.getPatientById(id), OK);
    }

    @GetMapping("/patient/lastName")
    public ResponseEntity<List<Patient>> getPatientByLastName(@RequestParam String lastName) {
        logger.info("get patient by lastname :{} request", lastName);
        return new ResponseEntity<>(patientService.getPatientsByLastName(lastName), OK);
    }

    @GetMapping("/searchPatient")
    public  ResponseEntity<List<Patient>> patientSearch (String firstName, String lastName) {
        logger.info("Send search patient named: {} {}", firstName, lastName);
        //return new ResponseEntity<>(patientService.getByPatientName(model,firstName,lastName), OK) ;
        List<Patient> patients = patientService.searchPatientsByName(firstName, lastName);
        return new ResponseEntity<>(patients, OK);
    }

    @PostMapping("/patient/add")
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        logger.info("save patient :{} {}", patient.getFirstName(), patient.getLastName());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    @PutMapping("/patient/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) {
        logger.info("update patient id :{}", patient.getId());
        return new ResponseEntity<>(patientService.updatePatient(patient), OK);
    }

    @DeleteMapping("/patient/delete")
    public ResponseEntity<Patient> deletePatientById(@RequestParam Long id) throws DataNotFoundException {
        logger.info("remove patient with id:{}  request", id);
        return new ResponseEntity<>(patientService.deletePatientById(id), OK);
    }

}
