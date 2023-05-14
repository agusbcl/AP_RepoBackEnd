package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.dtoSkills;
import com.portfolio.ab.Entity.Skills;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.SkillsService;
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
@CrossOrigin
public class SkillsController {

    @Autowired
    SkillsService skillsService;

    @GetMapping("skills/list")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("skills/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.NOT_FOUND);
        }
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @PostMapping("skills/create")

    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills) {
        if (StringUtils.isBlank(dtoskills.getSkillName())) {
            return new ResponseEntity(new Message("Skill name is required"), HttpStatus.BAD_REQUEST);
        }
        if (skillsService.existsByName(dtoskills.getSkillName())) {
            return new ResponseEntity(new Message("Skill already exists"), HttpStatus.BAD_REQUEST);
        }

        Skills skill = new Skills(dtoskills.getSkillName(), dtoskills.getPercent());
        skillsService.save(skill);

        return new ResponseEntity(new Message("Skill added successfully!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("skills/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        skillsService.delete(id);

        return new ResponseEntity(new Message("Skill deleted successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("skills/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskills) {

        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoskills.getSkillName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }

        if (skillsService.existsByName(dtoskills.getSkillName()) && skillsService.getByName(dtoskills.getSkillName()).get().getId() != id) {
            return new ResponseEntity(new Message("Skill already exists"), HttpStatus.BAD_REQUEST);
        }

        Skills skill = skillsService.getOne(id).get();
        skill.setSkillName(dtoskills.getSkillName());
        skill.setPercent(dtoskills.getPercent());

        skillsService.save(skill);
        return new ResponseEntity(new Message("Skill modified successfully"), HttpStatus.OK);
    }

}
