
package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.Education;
import com.portfolio.ab.Repository.EducationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducationService {
    @Autowired
    EducationRepository educationRepository;
    
    public List<Education> list(){
        return educationRepository.findAll();
    }
    
    public Optional<Education> getOne(int id){
        return educationRepository.findById(id);
    }
    
    public Optional<Education> getByEduName(String eduName){
        return educationRepository.findByEduName(eduName);
    }
    
    public void save(Education education){
        educationRepository.save(education);
    }
    
    public void deleteById(int id){
        educationRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return educationRepository.existsById(id);
    }
    
    public boolean existsByEduName(String eduName){
        return educationRepository.existsByEduName(eduName);
    }
}