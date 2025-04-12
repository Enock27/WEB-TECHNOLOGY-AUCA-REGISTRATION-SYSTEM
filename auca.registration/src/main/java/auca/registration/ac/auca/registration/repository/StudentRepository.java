package auca.registration.ac.auca.registration.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.registration.ac.auca.registration.model.Student;
import auca.registration.ac.auca.registration.model.StudentRegistration;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    
    Optional<Student> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email); 

    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName); 

    List<Student> findByRegistrations(List<StudentRegistration> registrations);

    boolean existsByEmail(String email);

}