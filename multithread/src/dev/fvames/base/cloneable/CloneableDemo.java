package dev.fvames.base.cloneable;

/**
 * Created by James on 2018/11/23.
 */
public class CloneableDemo {

    public static void main(String[] args) throws Exception {
        Email email = new Email("new job");
        User user = new User("james", email);

        User user1 = (User) user.clone();
        user1.getEmail().setContext("get job...");

        System.out.println(user);
        System.out.println(user1);
    }
}
