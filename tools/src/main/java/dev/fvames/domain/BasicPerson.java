package dev.fvames.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BasicPerson {
    private String name;
    private Integer age;
    private Date birthDate;
    private Integer sex;
}
