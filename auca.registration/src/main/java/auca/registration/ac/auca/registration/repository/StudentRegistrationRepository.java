package auca.registration.ac.auca.registration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.registration.ac.auca.registration.model.Course;
import auca.registration.ac.auca.registration.model.Semester;
import auca.registration.ac.auca.registration.model.StudentRegistration;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, UUID> {

    List<StudentRegistration> findByRegNumber(String regNumber);

    List<StudentRegistration> findBySemester(Semester semester);

    List<StudentRegistration> findByCourses(Course course);
}

