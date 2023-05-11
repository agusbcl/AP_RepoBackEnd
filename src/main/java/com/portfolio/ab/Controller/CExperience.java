package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.dtoExperience;
import com.portfolio.ab.Entity.JobExperience;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.SExperience;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobexperience")
@CrossOrigin(origins = "http://localhost:4200")

public class CExperience {

    @Autowired
    SExperience sExperience;

    @GetMapping("/list")
    public ResponseEntity<List<JobExperience>> list() {
        List<JobExperience> list = sExperience.list();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoExperience) {
        if (StringUtils.isBlank(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (sExperience.existsByJobName(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Job experience already exists"), HttpStatus.BAD_REQUEST);
        }

        JobExperience jobExperience = new JobExperience(dtoExperience.getJobName(), dtoExperience.getJobDescription());
        sExperience.save(jobExperience);

        return new ResponseEntity(new Message("Job Experience added successfully!"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoExperience) {
        //Check if Id exists //
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        //Name can't be blank
        if (StringUtils.isBlank(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        //Check if Job Name already exists
        if (sExperience.existsByJobName(dtoExperience.getJobName()) && sExperience.getByJobName(dtoExperience.getJobName()).get().getId() != id) {
            return new ResponseEntity(new Message("Job Experience already exists"), HttpStatus.BAD_REQUEST);
        }

        JobExperience jobExperience = sExperience.getOne(id).get();
        jobExperience.setJobName(dtoExperience.getJobName());
        jobExperience.setJobDescription(dtoExperience.getJobDescription());

        sExperience.save(jobExperience);
        return new ResponseEntity(new Message("Experience modified successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        sExperience.deleteById(id);

        return new ResponseEntity(new Message("Job Experience deleted successfully"), HttpStatus.OK);
    }

}
