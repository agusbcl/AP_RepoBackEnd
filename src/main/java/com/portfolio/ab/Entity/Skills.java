package com.portfolio.ab.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String skillName;
    private int percent;

    public Skills() {
    }

    public Skills(String skillName, int percent) {
        this.skillName = skillName;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
