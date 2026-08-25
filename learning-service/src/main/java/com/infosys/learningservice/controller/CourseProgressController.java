package com.infosys.learningservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infosys.learningservice.dto.CourseProgressDTO;
import com.infosys.learningservice.service.CourseProgressService;

@RestController
@RequestMapping("/progress")
public class CourseProgressController {

    private final CourseProgressService courseProgressService;

    public CourseProgressController(CourseProgressService courseProgressService) {
        this.courseProgressService = courseProgressService;
    }

    @PostMapping
    public ResponseEntity<CourseProgressDTO> addProgress(
            @RequestBody CourseProgressDTO progressDTO) {

        CourseProgressDTO savedProgress =
                courseProgressService.addProgress(progressDTO);

        return new ResponseEntity<>(savedProgress, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CourseProgressDTO> getAllProgress() {
        return courseProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public CourseProgressDTO getProgressById(@PathVariable Long id) {
        return courseProgressService.getProgressById(id);
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public List<CourseProgressDTO> getProgressByEnrollmentId(
            @PathVariable Long enrollmentId) {

        return courseProgressService.getProgressByEnrollmentId(enrollmentId);
    }

    @PutMapping("/{id}")
    public CourseProgressDTO updateProgress(
            @PathVariable Long id,
            @RequestBody CourseProgressDTO progressDTO) {

        return courseProgressService.updateProgress(id, progressDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgress(@PathVariable Long id) {

        courseProgressService.deleteProgress(id);

        return ResponseEntity.ok("Course Progress deleted successfully");
    }
}