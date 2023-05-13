package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.dtoEducation;
import com.portfolio.ab.Entity.Education;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.EducationService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = "http://localhost:4200")

public class EducationController {

    @Autowired
    EducationService educationService;

    @GetMapping("education/list")
    public ResponseEntity<List<Education>> list() {
        List<Education> list = educationService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("education/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("Education doesn't exist"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PostMapping("education/create")

    public ResponseEntity<?> create(@RequestBody dtoEducation dtoEducation) {
        if (StringUtils.isBlank(dtoEducation.getEduName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (educationService.existsByEduName(dtoEducation.getEduName())) {
            return new ResponseEntity(new Message("Education already exists"), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(dtoEducation.getEduName(), dtoEducation.getEduDescription());
        educationService.save(education);

        return new ResponseEntity(new Message("Education added successfully!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("education/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoEducation) {
        //Check if Id exists //
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        //Name can't be blank
        if (StringUtils.isBlank(dtoEducation.getEduName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        //Check if Education Name already exists
        if (educationService.existsByEduName(dtoEducation.getEduName()) && educationService.getByEduName(dtoEducation.getEduName()).get().getId() != id) {
            return new ResponseEntity(new Message("Education already exists"), HttpStatus.BAD_REQUEST);
        }

        Education education = educationService.getOne(id).get();
        education.setEduName(dtoEducation.getEduName());
        education.setEduDescription(dtoEducation.getEduDescription());

        educationService.save(education);
        return new ResponseEntity(new Message("Education modified successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("education/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        educationService.deleteById(id);

        return new ResponseEntity(new Message("Education deleted successfully"), HttpStatus.OK);
    }

}
