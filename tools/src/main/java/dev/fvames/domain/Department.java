package dev.fvames.domain;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    private Long id;
    private String deptName;
    private List<User> userList;
    private String[] userNames;
    private List<String> deptNames;

}
