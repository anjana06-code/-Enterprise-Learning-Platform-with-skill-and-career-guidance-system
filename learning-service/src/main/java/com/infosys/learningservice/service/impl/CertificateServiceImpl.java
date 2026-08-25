package com.infosys.learningservice.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infosys.learningservice.dto.CertificateDTO;
import com.infosys.learningservice.entity.Certificate;
import com.infosys.learningservice.repository.CertificateRepository;
import com.infosys.learningservice.service.CertificateService;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(
            CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public CertificateDTO generateCertificate(
            CertificateDTO certificateDTO) {

        Certificate certificate = new Certificate();

        certificate.setEnrollmentId(
                certificateDTO.getEnrollmentId());

        certificate.setCourseName(
                certificateDTO.getCourseName());

        certificate.setCertificateNumber(
                generateCertificateNumber());

        if (certificateDTO.getIssueDate() == null) {
            certificate.setIssueDate(LocalDate.now());
        } else {
            certificate.setIssueDate(
                    certificateDTO.getIssueDate());
        }

        if (certificateDTO.getStatus() == null
                || certificateDTO.getStatus().isBlank()) {
            certificate.setStatus("ISSUED");
        } else {
            certificate.setStatus(
                    certificateDTO.getStatus());
        }

        Certificate savedCertificate =
                certificateRepository.save(certificate);

        return convertToDTO(savedCertificate);
    }

    @Override
    public List<CertificateDTO> getAllCertificates() {

        return certificateRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDTO getCertificateById(Long id) {

        Certificate certificate =
                certificateRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Certificate not found with id: "
                                                + id));

        return convertToDTO(certificate);
    }

    @Override
    public List<CertificateDTO> getCertificatesByEnrollmentId(
            Long enrollmentId) {

        return certificateRepository
                .findByEnrollmentId(enrollmentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDTO updateCertificate(
            Long id,
            CertificateDTO certificateDTO) {

        Certificate certificate =
                certificateRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Certificate not found with id: "
                                                + id));

        certificate.setEnrollmentId(
                certificateDTO.getEnrollmentId());

        certificate.setCourseName(
                certificateDTO.getCourseName());

        certificate.setIssueDate(
                certificateDTO.getIssueDate());

        certificate.setStatus(
                certificateDTO.getStatus());

        Certificate updatedCertificate =
                certificateRepository.save(certificate);

        return convertToDTO(updatedCertificate);
    }

    @Override
    public void deleteCertificate(Long id) {

        Certificate certificate =
                certificateRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Certificate not found with id: "
                                                + id));

        certificateRepository.delete(certificate);
    }

    private String generateCertificateNumber() {

        return "CERT-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }

    private CertificateDTO convertToDTO(
            Certificate certificate) {

        CertificateDTO dto = new CertificateDTO();

        dto.setCertificateId(
                certificate.getCertificateId());

        dto.setEnrollmentId(
                certificate.getEnrollmentId());

        dto.setCertificateNumber(
                certificate.getCertificateNumber());

        dto.setCourseName(
                certificate.getCourseName());

        dto.setIssueDate(
                certificate.getIssueDate());

        dto.setStatus(
                certificate.getStatus());

        return dto;
    }
}