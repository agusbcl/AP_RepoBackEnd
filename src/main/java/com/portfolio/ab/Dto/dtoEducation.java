package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoEducation {
    @NotBlank
    private String eduName;
    @NotBlank
    private String eduDescription;    

    public dtoEducation() {
    }

    public dtoEducation(String eduName, String eduDescription) {
        this.eduName = eduName;
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
    
    
    
}
