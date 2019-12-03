package dev.fvames.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BasicPersonDto {
    private String fullName;
    private int currentAge;
    private Date birthDate;
    private int sex;
}
