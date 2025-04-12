package auca.registration.ac.auca.registration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.registration.ac.auca.registration.model.Course;
import auca.registration.ac.auca.registration.model.StudentRegistration;

public interface CourseRepository extends JpaRepository<Course, UUID>{
    
    List<Course> findByRegistrations(StudentRegistration registration);
}
