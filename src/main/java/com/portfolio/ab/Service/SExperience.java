package com.portfolio.ab.Service;

import com.portfolio.ab.Entity.JobExperience;
import com.portfolio.ab.Repository.RExperience;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperience {

    @Autowired
    RExperience rExperience;

    public List<JobExperience> list() {
        return rExperience.findAll();
    }

    public Optional<JobExperience> getOne(int id) {
        return rExperience.findById(id);
    }

    public Optional<JobExperience> getByJobName(String jobName) {
        return rExperience.findByJobName(jobName);
    }

    public void save(JobExperience jobExp) {
        rExperience.save(jobExp);
    }

    public void deleteById(int id) {
        rExperience.deleteById(id);
    }

    public boolean existsById(int id) {
        return rExperience.existsById(id);
    }

    public boolean existsByJobName(String jobName) {
        return rExperience.existsByJobName(jobName);
    }

}
