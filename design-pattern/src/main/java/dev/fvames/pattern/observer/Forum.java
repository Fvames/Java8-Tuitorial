package dev.fvames.pattern.observer;

import java.util.Observable;

public class Forum extends Observable {
    private String name = "Chiphel ";
    private static Forum forum = null;

    public static Forum getInstance() {
        if (forum == null) {
            forum = new Forum();
        }
        return forum;
    }

    public String getName() {
        return name;
    }

    public void publisQuestion(Article article) {
        System.out.printf("%s 在 %s 上发了一个测试分享文章。", article.getUserName(), name);
        setChanged();
        notifyObservers(article);
    }
}
