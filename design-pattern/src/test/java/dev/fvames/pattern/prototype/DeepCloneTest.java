package dev.fvames.pattern.prototype;

/**
 * 深克隆
 */

public class DeepCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone deepClone = new DeepClone();
        deepClone.setId(2L);
        DeepUser user = new DeepUser();
        user.setUserName("安迪");
        deepClone.setUser(user);

        DeepClone deepClone1 = (DeepClone) deepClone.clone();

        System.out.println(deepClone == deepClone1); // false
        System.out.println(deepClone.getUser() == deepClone1.getUser()); // false
    }
}
