package com.james.java8.samples.optional;

import com.james.java8.samples.model.User;

import java.util.Optional;

/**
 * Optional 非空判断
 *
 * @version 2019/4/30 15:11
 */

public class OptionalDemo1 {

    public static void main(String[] args) {
        //testBase();

        User user = new User(1L, "兰尼斯特", "多特大陆", 30L);
        Optional<User> userOptional = Optional.of(user);
        String userName = userOptional.map(User::getUserName).orElse("夜王");
        System.out.println(userName);

        System.out.println("---------------");
        Optional<User> userOptional2 = Optional.ofNullable(null);
        String userName1 = userOptional2.map(User::getUserName).orElse("夜王");
        System.out.println(userName1);
    }

    private static void testBase() {
        //Optional<String> str = Optional.of(null); // 异常
        //System.out.println(str);

        Optional<String> str1 = Optional.ofNullable(null);
        //System.out.println(str1.get()); // exception： no value present
        System.out.println(str1.orElse("null else value"));
        System.out.println(str1.orElseGet(() -> {
            return "sdkdks";
        }));
        str1.orElseThrow(() -> new RuntimeException("数据为空"));
    }

}
