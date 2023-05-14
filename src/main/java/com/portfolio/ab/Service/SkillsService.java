package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.Skills;
import com.portfolio.ab.Repository.SkillsRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SkillsService {

    @Autowired
    SkillsRepository skillsRepository;

    public List<Skills> list() {
        return skillsRepository.findAll();

    }
    public Optional<Skills> getOne(int id){
        return skillsRepository.findById(id);
    }
    
    public Optional<Skills> getByName(String name){
        return skillsRepository.findBySkillName(name);
    }
    
    public void save(Skills skill){
        skillsRepository.save(skill);
    }
    
    public void delete(int id){
        skillsRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return skillsRepository.existsById(id);
    }
    
    public boolean existsByName(String name){
        return skillsRepository.existsBySkillName(name);
    }
}
