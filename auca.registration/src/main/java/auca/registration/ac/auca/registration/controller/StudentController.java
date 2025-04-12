package auca.registration.ac.auca.registration.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import auca.registration.ac.auca.registration.model.Student;
import auca.registration.ac.auca.registration.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/student")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from the frontend
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/saveStudent", 
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student) {
        try {
            // First check if email exists
            if (studentService.existsByEmail(student.getEmail())) {
                return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body("User with this email already exists");
            }
            
            // If not, proceed with saving
            String response = studentService.saveStudent(student);
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            // This catches database-level unique constraint violations
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("User with this email already exists");
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error saving student: " + e.getMessage());
        }
    }

    @GetMapping(value = "/getAllStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error fetching students: " + e.getMessage());
        }
    }

    @PutMapping(value = "/updateStudent/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@PathVariable UUID id, @Valid @RequestBody Student updatedStudent) {
        try {
            String response = studentService.updateStudent(id, updatedStudent);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating student: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID id) {
        try {
            String response = studentService.deleteStudent(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting student: " + e.getMessage());
        }
    }
}