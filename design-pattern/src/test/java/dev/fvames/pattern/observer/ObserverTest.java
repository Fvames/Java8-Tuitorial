package dev.fvames.pattern.observer;

public class ObserverTest {

    public static void main(String[] args) {
        Forum forum = new Forum();
        User lili = new User("Lili");
        User James = new User("James");

        forum.addObserver(lili);
        forum.addObserver(James);

        Article article = new Article();
        article.setUserName("莫斯科眼泪");
        article.setContent("大疆飞行器优雅、极速");

        forum.publisQuestion(article);
    }
}
