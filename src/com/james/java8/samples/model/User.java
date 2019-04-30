package com.james.java8.samples.model;

/**
 * 用户 model
 *
 * @version 2019/4/29 15:02
 */

public class User {
    private Long id;
    private String userName;
    private String dept;
    private Long age;

    public User(Long id, String userName, String dept, Long age) {
        this.id = id;
        this.userName = userName;
        this.dept = dept;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", dept='" + dept + '\'' +
                ", age=" + age +
                '}';
    }
}
