package com.infosys.learningservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.learningservice.dto.CertificateDTO;
import com.infosys.learningservice.service.CertificateService;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(
            CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public ResponseEntity<CertificateDTO> generateCertificate(
            @RequestBody CertificateDTO certificateDTO) {

        CertificateDTO savedCertificate =
                certificateService.generateCertificate(
                        certificateDTO);

        return new ResponseEntity<>(
                savedCertificate,
                HttpStatus.CREATED);
    }

    @GetMapping
    public List<CertificateDTO> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @GetMapping("/{id}")
    public CertificateDTO getCertificateById(
            @PathVariable Long id) {

        return certificateService.getCertificateById(id);
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public List<CertificateDTO> getByEnrollmentId(
            @PathVariable Long enrollmentId) {

        return certificateService
                .getCertificatesByEnrollmentId(enrollmentId);
    }

    @PutMapping("/{id}")
    public CertificateDTO updateCertificate(
            @PathVariable Long id,
            @RequestBody CertificateDTO certificateDTO) {

        return certificateService.updateCertificate(
                id,
                certificateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertificate(
            @PathVariable Long id) {

        certificateService.deleteCertificate(id);

        return ResponseEntity.ok(
                "Certificate deleted successfully");
    }
}