package com.portfolio.ab.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "Name requires at least one character")
    private String name;

    @NotNull
    @Size(min = 1, max = 50, message = "Last Name requires at least one character")
    private String lastName;

    @Size(min = 1, max = 50, message = "img requires at least one character")
    private String img;

}
