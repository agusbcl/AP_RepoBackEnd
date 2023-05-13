package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.JobExperience;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.ab.Repository.IJobExperienceRepository;

@Service
@Transactional
public class JobExperienceService {

    @Autowired
    IJobExperienceRepository rJobExperience;

    public List<JobExperience> list() {
        return rJobExperience.findAll();
    }

    public Optional<JobExperience> getOne(int id) {
        return rJobExperience.findById(id);
    }

    public Optional<JobExperience> getByJobName(String jobName) {
        return rJobExperience.findByJobName(jobName);
    }

    public void save(JobExperience jobExp) {
        rJobExperience.save(jobExp);
    }

    public void deleteById(int id) {
        rJobExperience.deleteById(id);
    }

    public boolean existsById(int id) {
        return rJobExperience.existsById(id);
    }

    public boolean existsByJobName(String jobName) {
        return rJobExperience.existsByJobName(jobName);
    }

}
