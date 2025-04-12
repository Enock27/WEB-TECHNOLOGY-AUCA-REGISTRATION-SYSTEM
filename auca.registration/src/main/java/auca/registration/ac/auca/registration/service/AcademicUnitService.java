package auca.registration.ac.auca.registration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.registration.ac.auca.registration.model.AcademicUnit;
import auca.registration.ac.auca.registration.repository.AcademicUnitRepository;

@Service
public class AcademicUnitService {
    

    @Autowired
    private AcademicUnitRepository academicUnitRepository;
    

    public String saveProgramme(AcademicUnit unity){

        if(academicUnitRepository.existsByNameAndCode(unity.getName(), unity.getCode())){
            return "Programme with this name: " + unity.getName()+" and code: "+unity.getCode()+" already exists";
        }else{
            academicUnitRepository.save(unity);
            return "Programme saved successfully";
        }
        
    }

    public String saveFacultyOrDepartment(AcademicUnit unit, String name, String code){

        //fetch the parent
        Optional<AcademicUnit> getParent = academicUnitRepository.findByNameAndCode(name, code);
        if(getParent.isPresent()){
            if(academicUnitRepository.existsByNameAndCode(unit.getName(), unit.getCode())){
                return "Faculty or Department with this name: " + unit.getName()+" and code: "+unit.getCode()+" already exists";
            }else{
                unit.setAcademicUnit(getParent.get());
                academicUnitRepository.save(unit);
                return "Faculty or Department saved successfully";
            }
            
        }else{
            return "The parent not found";
        }
    }

    public AcademicUnit getAcademicUnit( String code){
        return academicUnitRepository.findByCode( code);
    }
}
