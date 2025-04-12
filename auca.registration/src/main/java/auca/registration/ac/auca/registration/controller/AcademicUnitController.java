package auca.registration.ac.auca.registration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.registration.ac.auca.registration.model.AcademicUnit;
import auca.registration.ac.auca.registration.service.AcademicUnitService;

@RestController
@RequestMapping(value = "/academicUnit")
public class AcademicUnitController {
    

    @Autowired
    private AcademicUnitService academicService;

    @PostMapping(value="/saveProgram", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProgram(@RequestBody AcademicUnit unity){
        
        String saveProgram = academicService.saveProgramme(unity);

        if(saveProgram.equals("Programme saved successfully")){
            return new ResponseEntity<>(saveProgram, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(saveProgram, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value="/saveFacultyOrDepartment", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)  //add parameters for parent name and code to save faculty or department under a parent programme or department
    public ResponseEntity<?> saveFacultyOrDepartment(@RequestBody AcademicUnit unity, 
    @RequestParam String name, @RequestParam String code){
        String saveFacultyOrDepartment = academicService.saveFacultyOrDepartment(unity,name, code);

        if(saveFacultyOrDepartment.equals("Faculty or Department saved successfully")){
            return new ResponseEntity<>(saveFacultyOrDepartment, HttpStatus.OK);
        }else if(saveFacultyOrDepartment.equals("The parent not found")){
            return new ResponseEntity<>(saveFacultyOrDepartment, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(saveFacultyOrDepartment, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getAcademicUnit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAcedemicUnit(@RequestParam String code){
        AcademicUnit academicUnit = academicService.getAcademicUnit(code);
        if(academicUnit != null){
            return new ResponseEntity<>(academicUnit, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Academic Unit not found", HttpStatus.NOT_FOUND);
        }
    }



}
