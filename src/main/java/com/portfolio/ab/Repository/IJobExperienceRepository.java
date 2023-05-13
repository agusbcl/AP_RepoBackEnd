
package com.portfolio.ab.Repository;

import com.portfolio.ab.Entity.JobExperience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobExperienceRepository extends JpaRepository<JobExperience, Integer> {
    
    public Optional<JobExperience> findByJobName(String jobName);
    public boolean existsByJobName(String jobName);
}
