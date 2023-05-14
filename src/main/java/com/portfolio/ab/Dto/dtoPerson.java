package com.portfolio.ab.Dto;

import jakarta.validation.constraints.NotBlank;



public class dtoPerson {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public dtoPerson() {
    }

    public dtoPerson(String name, String lastName, String description, String img) {
        this.name = name;
        this.lastName = lastName;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
