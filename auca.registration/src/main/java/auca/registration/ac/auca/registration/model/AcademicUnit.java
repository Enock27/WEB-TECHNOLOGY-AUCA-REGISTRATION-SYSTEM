package auca.registration.ac.auca.registration.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="academic_unit")
public class AcademicUnit {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="academic_type")
    @Enumerated(EnumType.STRING)
    private EAcademicUnit academicType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_id" ,nullable = true)
    private AcademicUnit academicUnit;

    @OneToMany(mappedBy ="unity")
    List<StudentRegistration> registrations;

    

    public AcademicUnit() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EAcademicUnit getAcademicType() {
        return academicType;
    }

    public void setAcademicType(EAcademicUnit academicType) {
        this.academicType = academicType;
    }

    public AcademicUnit getAcademicUnit() {
        return academicUnit;
    }

    public void setAcademicUnit(AcademicUnit academicUnit) {
        this.academicUnit = academicUnit;
    }
    
}
