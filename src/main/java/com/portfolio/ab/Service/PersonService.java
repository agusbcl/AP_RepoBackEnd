package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.Person;
import com.portfolio.ab.Repository.IPersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonService {
    
    @Autowired IPersonRepository ipersonRepository;

    public List<Person> list(){
        return ipersonRepository.findAll();
    }
    
    public Optional<Person> getOne(int id){
        return ipersonRepository.findById(id);
    }
    
    public Optional<Person> getByName(String name){
        return ipersonRepository.findByName(name);
    }
    
    public void save(Person person){
        ipersonRepository.save(person);
    }
    
    public void deleteById(int id){
        ipersonRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return ipersonRepository.existsById(id);
    }
    
    public boolean existsByName(String name){
        return ipersonRepository.existsByName(name);
    }
    
}
