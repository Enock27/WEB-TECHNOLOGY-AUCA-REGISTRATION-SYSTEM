package auca.registration.ac.auca.registration.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="course_id")
    private UUID id;

    @Column(name = "code")
    private String code;
    
    @Column(name = "name")
    private String name;

  

    @ManyToMany(mappedBy = "courses")
    private List<StudentRegistration> registrations;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private AcademicUnit unity;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
