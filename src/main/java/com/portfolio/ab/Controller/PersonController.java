package com.portfolio.ab.Controller;

import com.portfolio.ab.Entity.Person;
import com.portfolio.ab.Interface.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class PersonController {

    @Autowired
    IPersonService ipersonService;
    
    @GetMapping("person/get")
    public List<Person> getPerson() {
        return ipersonService.getPerson();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("person/create")
    public String createPerson(@RequestBody Person person){
        ipersonService.savePerson(person);
        return "Person was created successfully";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("person/delete/{id}")
    public String deletePerson(@PathVariable Long id){
        ipersonService.deletePerson(id);
        return "Person deleted successfully";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("person/edit/{id}")
    public Person editPerson(@PathVariable Long id,
                            @RequestParam("name") String newName,
                            @RequestParam("lastName") String newLastName,
                            @RequestParam("img") String newImg){
        Person person = ipersonService.findPerson(id);
        
        person.setName(newName);
        person.setLastName(newLastName);
        person.setImg(newImg);
        
        ipersonService.savePerson(person);
        return person;
    }
    
    @GetMapping("/person/profile")
    public Person findPerson(){
        return ipersonService.findPerson((long)1);
    }
}
