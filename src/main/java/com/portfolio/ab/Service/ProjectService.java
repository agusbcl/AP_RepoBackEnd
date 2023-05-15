package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.Project;
import com.portfolio.ab.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    
    public List<Project> list(){
        return projectRepository.findAll();
    }
    
    public Optional<Project> getOne(int id){
        return projectRepository.findById(id);
    }
    
    public Optional<Project> getByProjectName(String projectName){
        return projectRepository.findByProjectName(projectName);
    }
    
    public void save(Project project){
        projectRepository.save(project);
    }
    
    public void deleteById(int id){
        projectRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return projectRepository.existsById(id);
    }
    
    public boolean existsByProjectName(String projectName){
        return projectRepository.existsByProjectName(projectName);
    }
    
}
