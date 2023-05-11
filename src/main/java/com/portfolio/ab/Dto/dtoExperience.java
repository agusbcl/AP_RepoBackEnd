package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;

public class dtoExperience {

    @NotBlank
    private String jobName;
    @NotBlank
    private String jobDescription;
    
    //Constructor

    public dtoExperience() {
    }

    public dtoExperience(String jobName, String jobDescription) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
    }
    
    //Getter and Setter

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    
    
    
}
