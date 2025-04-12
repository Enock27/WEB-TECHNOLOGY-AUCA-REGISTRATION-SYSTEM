package auca.registration.ac.auca.registration.model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "student_registration")
public class StudentRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "registration_id")
    private UUID id;

    @Column(name = "reg_number")
    private String regNumber;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")  
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    private AcademicUnit unity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "registration_course",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;




    // Default constructor
    public StudentRegistration() {}

    

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
