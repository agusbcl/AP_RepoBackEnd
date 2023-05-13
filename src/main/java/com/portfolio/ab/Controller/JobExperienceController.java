package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.dtoExperience;
import com.portfolio.ab.Entity.JobExperience;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.JobExperienceService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class JobExperienceController {

    @Autowired
    JobExperienceService jobExperienceService;

    @GetMapping("jobexperience/list")
    public ResponseEntity<List<JobExperience>> list() {
        List<JobExperience> list = jobExperienceService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("jobexperience/detail/{id}")
    public ResponseEntity<JobExperience> getById(@PathVariable("id") int id) {
        if (!jobExperienceService.existsById(id)) {
            return new ResponseEntity(new Message("Job experience doesn't exist"), HttpStatus.NOT_FOUND);
        }
        JobExperience jobExperience = jobExperienceService.getOne(id).get();
        return new ResponseEntity(jobExperience, HttpStatus.OK);
    }

    @PostMapping("jobexperience/create")

    public ResponseEntity<?> create(@RequestBody dtoExperience dtoExperience) {
        if (StringUtils.isBlank(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (jobExperienceService.existsByJobName(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Job experience already exists"), HttpStatus.BAD_REQUEST);
        }

        JobExperience jobExperience = new JobExperience(dtoExperience.getJobName(), dtoExperience.getJobDescription());
        jobExperienceService.save(jobExperience);

        return new ResponseEntity(new Message("Job Experience added successfully!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("jobexperience/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoExperience) {
        //Check if Id exists //
        if (!jobExperienceService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        //Name can't be blank
        if (StringUtils.isBlank(dtoExperience.getJobName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        //Check if Job Name already exists
        if (jobExperienceService.existsByJobName(dtoExperience.getJobName()) && jobExperienceService.getByJobName(dtoExperience.getJobName()).get().getId() != id) {
            return new ResponseEntity(new Message("Job Experience already exists"), HttpStatus.BAD_REQUEST);
        }

        JobExperience jobExperience = jobExperienceService.getOne(id).get();
        jobExperience.setJobName(dtoExperience.getJobName());
        jobExperience.setJobDescription(dtoExperience.getJobDescription());

        jobExperienceService.save(jobExperience);
        return new ResponseEntity(new Message("Experience modified successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("jobexperience/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!jobExperienceService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        jobExperienceService.deleteById(id);

        return new ResponseEntity(new Message("Job Experience deleted successfully"), HttpStatus.OK);
    }

}
