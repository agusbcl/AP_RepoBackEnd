package com.portfolio.ab.Interface;

import com.portfolio.ab.Entity.Person;
import java.util.List;

public interface IPersonService {
    //Show lista Person
    
    public List<Person> getPerson();
    
    //Save obj Person
    
    public void savePerson(Person person);
    
    //Delete person
    
    public void deletePerson(Long id);
    
    //Find Person
    
    public Person findPerson(Long id);
}
