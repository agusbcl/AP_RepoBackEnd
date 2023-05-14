package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.dtoPerson;
import com.portfolio.ab.Entity.Person;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.PersonService;
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

public class PersonController {
@Autowired
    PersonService personService;

    @GetMapping("person/list")
    public ResponseEntity<List<Person>> list() {
        List<Person> list = personService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("person/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") int id) {
        if (!personService.existsById(id)) {
            return new ResponseEntity(new Message("Person doesn't exist"), HttpStatus.NOT_FOUND);
        }
        Person person = personService.getOne(id).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PostMapping("person/create")

    public ResponseEntity<?> create(@RequestBody dtoPerson dtoPerson) {
        if (StringUtils.isBlank(dtoPerson.getName())) {
            return new ResponseEntity(new Message("Person Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (personService.existsByName(dtoPerson.getName())) {
            return new ResponseEntity(new Message("The Person you're trying to add already exists"), HttpStatus.BAD_REQUEST);
        }

        Person person = new Person(dtoPerson.getName(), dtoPerson.getLastName(), dtoPerson.getDescription(), dtoPerson.getImg());
        personService.save(person);

        return new ResponseEntity(new Message("Person added successfully!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("person/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPerson dtoPerson) {
        //Check if Id exists //
        if (!personService.existsById(id)) {
            return new ResponseEntity(new Message("Person Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        //Name can't be blank
        if (StringUtils.isBlank(dtoPerson.getName())) {
            return new ResponseEntity(new Message("Person Name is required"), HttpStatus.BAD_REQUEST);
        }
        //Check if Person Name already exists
        if (personService.existsByName(dtoPerson.getName()) && personService.getByName(dtoPerson.getName()).get().getId() != id) {
            return new ResponseEntity(new Message("Person name already exists, try another"), HttpStatus.BAD_REQUEST);
        }

        Person person = personService.getOne(id).get();
        person.setName(dtoPerson.getName());
        person.setLastName(dtoPerson.getLastName());
        person.setDescription(dtoPerson.getDescription());
        person.setImg(dtoPerson.getImg());

        personService.save(person);
        return new ResponseEntity(new Message("Person modified successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("person/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personService.existsById(id)) {
            return new ResponseEntity(new Message("Person Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        personService.deleteById(id);

        return new ResponseEntity(new Message("Person deleted successfully"), HttpStatus.OK);
    }

}
