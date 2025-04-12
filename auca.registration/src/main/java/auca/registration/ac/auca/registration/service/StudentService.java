package auca.registration.ac.auca.registration.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.ac.auca.registration.model.Student;
import auca.registration.ac.auca.registration.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(Student student) {
        boolean emailExists = studentRepository.existsByEmail(student.getEmail());
        System.out.println("Checking if email exists: " + emailExists); // Debugging log
        if (emailExists) {
            System.out.println("Duplicate email detected: " + student.getEmail());
            throw new RuntimeException("Email already exists");
        }
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new RuntimeException("No students found in the database.");
        }
        return students;
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    public String updateStudent(UUID id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setLocation(updatedStudent.getLocation());
        studentRepository.save(student);
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    public Optional<Student> getStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }
}