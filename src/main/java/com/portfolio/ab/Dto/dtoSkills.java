package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;

public class dtoSkills {
    
    @NotBlank
    private String skillName;
    @NotBlank
    private int percent;

    public dtoSkills() {
    }

    public dtoSkills(String skillName, int percent) {
        this.skillName = skillName;
        this.percent = percent;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    
}
