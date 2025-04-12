package auca.registration.ac.auca.registration.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auca.registration.ac.auca.registration.model.AcademicUnit;

@Repository
public interface AcademicUnitRepository extends JpaRepository<AcademicUnit, UUID> {

    boolean existsByNameAndCode(String name, String code);
    
    Optional <AcademicUnit> findByNameAndCode(String name, String code);

    AcademicUnit findByCode( String code);
}
