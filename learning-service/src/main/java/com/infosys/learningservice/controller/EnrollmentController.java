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

import com.infosys.learningservice.dto.EnrollmentDTO;
import com.infosys.learningservice.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> addEnrollment(
            @RequestBody EnrollmentDTO enrollmentDTO) {

        EnrollmentDTO savedEnrollment =
                enrollmentService.addEnrollment(enrollmentDTO);

        return new ResponseEntity<>(
                savedEnrollment, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public EnrollmentDTO getEnrollmentById(
            @PathVariable Long id) {

        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EnrollmentDTO> getByEmployeeId(
            @PathVariable Long employeeId) {

        return enrollmentService
                .getEnrollmentsByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public EnrollmentDTO updateEnrollment(
            @PathVariable Long id,
            @RequestBody EnrollmentDTO enrollmentDTO) {

        return enrollmentService
                .updateEnrollment(id, enrollmentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteEnrollment(@PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return "Enrollment deleted successfully";
    }
}