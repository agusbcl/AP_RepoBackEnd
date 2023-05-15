package com.portfolio.ab.Controller;

import com.portfolio.ab.Dto.DtoProject;
import com.portfolio.ab.Entity.Project;
import com.portfolio.ab.Security.Controller.Message;
import com.portfolio.ab.Service.ProjectService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("project/list")
    public ResponseEntity<List<Project>> list() {
        List<Project> list = projectService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("project/detail/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") int id) {
        if (!projectService.existsById(id)) {
            return new ResponseEntity(new Message("Project doesn't exist"), HttpStatus.NOT_FOUND);
        }
        Project project = projectService.getOne(id).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @PostMapping("project/create")

    public ResponseEntity<?> create(@RequestBody DtoProject dtoProjetc) {
        if (StringUtils.isBlank(dtoProjetc.getProjectName())) {
            return new ResponseEntity(new Message("Project Name is required"), HttpStatus.BAD_REQUEST);
        }
        if (projectService.existsByProjectName(dtoProjetc.getProjectName())) {
            return new ResponseEntity(new Message("Project name already exists"), HttpStatus.BAD_REQUEST);
        }

        Project project = new Project(dtoProjetc.getProjectName(), dtoProjetc.getProjectDescription(), dtoProjetc.getUrl());
        projectService.save(project);

        return new ResponseEntity(new Message("Project added successfully!"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("project/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProject dtoProject) {
        //Check if Id exists //
        if (!projectService.existsById(id)) {
            return new ResponseEntity(new Message("Project Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }
        //Name can't be blank
        if (StringUtils.isBlank(dtoProject.getProjectName())) {
            return new ResponseEntity(new Message("Name is required"), HttpStatus.BAD_REQUEST);
        }
        //Check if Project  already exists
        if (projectService.existsByProjectName(dtoProject.getProjectName()) && projectService.getByProjectName(dtoProject.getProjectName()).get().getId() != id) {
            return new ResponseEntity(new Message("Project already exists"), HttpStatus.BAD_REQUEST);
        }

        Project project = projectService.getOne(id).get();
        project.setProjectName(dtoProject.getProjectName());
        project.setProjectDescription(dtoProject.getProjectDescription());
        project.setUrl(dtoProject.getUrl());

        projectService.save(project);
        return new ResponseEntity(new Message("Project modified successfully"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("project/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!projectService.existsById(id)) {
            return new ResponseEntity(new Message("Id doesn't exist"), HttpStatus.BAD_REQUEST);
        }

        projectService.deleteById(id);

        return new ResponseEntity(new Message("Project deleted successfully"), HttpStatus.OK);
    }
}
