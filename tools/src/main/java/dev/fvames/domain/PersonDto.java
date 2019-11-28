package dev.fvames.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PersonDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String[][] aliases;
}
