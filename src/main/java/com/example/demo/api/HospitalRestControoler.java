package com.example.demo.api;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PatientDetails;
import com.example.demo.service.DetailsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HospitalRestControoler {
    @Autowired
    private DetailsService detailsService;

    @PostMapping("/dataUpload")
    public String upload(@RequestBody List<PatientDetails> patientDetails) {
        for (PatientDetails patientdetails : patientDetails) {
            detailsService.uploadData(patientdetails);
        }
        return "success";
    }
}
