package dev.fvames.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        Forum forum = (Forum) o;
        Article article = (Article) arg;
        System.out.println("====================");

        System.out.printf("用户 %s ，你好，您关注的用户 %s 在 %s 发表了一片测试报告，报告概要如下： %s", this.name, article.getUserName(), forum.getName(), article.getContent());
    }
}
