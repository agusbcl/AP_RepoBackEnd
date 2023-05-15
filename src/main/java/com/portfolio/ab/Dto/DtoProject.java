
package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;


public class DtoProject {
    @NotBlank
    private String projectName;
    @NotBlank
    private String projectDescription;  
    @NotBlank
    private String url;
    
    //Constructor
    public DtoProject() {
    }

    public DtoProject(String projectName, String projectDescription, String url) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.url = url;
    }
    
    //Getter and Setter
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
