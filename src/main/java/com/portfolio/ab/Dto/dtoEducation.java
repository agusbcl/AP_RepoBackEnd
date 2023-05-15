package com.portfolio.ab.Dto;


import jakarta.validation.constraints.NotBlank;
import java.sql.Date;


public class dtoEducation {
    @NotBlank
    private String eduName;
    @NotBlank
    private Date startDate;
    private Date endDate;
    @NotBlank
    private String eduDescription;    

    public dtoEducation() {
    }

    public dtoEducation(String eduName, Date startDate, Date endDate, String eduDescription) {
        this.eduName = eduName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eduDescription = eduDescription;
    }



    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public String getEduDescription() {
        return eduDescription;
    }

    public void setEduDescription(String eduDescription) {
        this.eduDescription = eduDescription;
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
