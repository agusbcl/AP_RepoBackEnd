package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;
import java.sql.Date;

public class dtoExperience {

    @NotBlank
    private String jobName;
    @NotBlank
    private Date startDate;
    private Date endDate;
    @NotBlank
    private String jobDescription;
    
    
    //Constructor

    public dtoExperience() {
    }

    public dtoExperience(String jobName, Date startDate, Date endDate, String jobDescription) {
        this.jobName = jobName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
