
package com.portfolio.ab.Repository;

import com.portfolio.ab.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SkillsRepository extends JpaRepository<Skills, Integer>{
    Optional<Skills> findBySkillName(String skillName);
    
    public boolean existsBySkillName(String skillName);
    
}
