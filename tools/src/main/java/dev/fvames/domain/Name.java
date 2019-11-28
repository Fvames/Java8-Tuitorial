package dev.fvames.domain;

import lombok.Data;

@Data
public class Name {
    private String first;
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }
}
