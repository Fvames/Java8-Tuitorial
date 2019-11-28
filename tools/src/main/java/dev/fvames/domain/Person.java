package dev.fvames.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Person {
    private Name name;
    private List<Name> knownAliases;
    private Date birthDate;

    public Person(Name name, List<Name> knownAliases, Date birthDate) {
        this.name = name;
        this.knownAliases = knownAliases;
        this.birthDate = birthDate;
    }
}
