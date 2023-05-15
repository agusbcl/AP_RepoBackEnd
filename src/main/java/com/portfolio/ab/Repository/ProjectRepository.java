package com.portfolio.ab.Repository;

import com.portfolio.ab.Entity.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    public Optional<Project> findByProjectName(String projectName);

    public boolean existsByProjectName(String projectName);

}
