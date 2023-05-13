package com.portfolio.ab.Repository;

import com.portfolio.ab.Entity.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

    public Optional<Education> findByEduName(String eduName);

    public boolean existsByEduName(String eduName);
}
