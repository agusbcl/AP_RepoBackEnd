package com.portfolio.ab.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String eduName;
    private String eduDescription;
    
    //Constructor

    public Education() {
    }

    public Education(String eduName, String eduDescription) {
        this.eduName = eduName;
        this.eduDescription = eduDescription;
    }
    
    //Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
