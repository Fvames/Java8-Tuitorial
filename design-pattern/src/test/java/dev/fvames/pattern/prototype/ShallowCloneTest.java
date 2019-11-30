package dev.fvames.pattern.prototype;

/**
 * 浅克隆测试
 */

public class ShallowCloneTest {

    public static void main(String[] args) throws Exception {
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.setId(1L);
        User user = new User();
        user.setUserName("清风");
        shallowClone.setUser(user);

        ShallowClone shallowClone1 = (ShallowClone) shallowClone.clone();

        System.out.println(shallowClone == shallowClone1); // false
        System.out.println(shallowClone.getUser() == shallowClone1.getUser()); // true
    }

}
