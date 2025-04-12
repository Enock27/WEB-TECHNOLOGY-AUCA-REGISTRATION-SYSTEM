package auca.registration.ac.auca.registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.ac.auca.registration.model.Student;
import auca.registration.ac.auca.registration.model.StudentRegistration;
import auca.registration.ac.auca.registration.repository.StudentRegistrationRepository;
import auca.registration.ac.auca.registration.repository.StudentRepository;

@Service
public class StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository regRepository;

    @Autowired
    private StudentRepository studentRepository;

    

    public String saveStudentRegistration(StudentRegistration registration, String firstName, String lastName) {
        Optional<Student> getStudent = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        if (getStudent.isPresent()) {
            registration.setStudent(getStudent.get());
            regRepository.save(registration);
            return "Registration saved successfully";
        } else {
          return "Student not found";
        }
    }

    public List<StudentRegistration> getStudentRegistrationsByRegNumber(String regNumber) {
        return regRepository.findByRegNumber(regNumber); 
    }

    
}