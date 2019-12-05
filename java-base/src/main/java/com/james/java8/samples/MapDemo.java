package com.james.java8.samples;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("1", "a");
        map.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

        map.computeIfAbsent("2", e -> e + "1"); // 当没有对应的值时才计算新值
        System.out.println(map.get("2"));

        map.computeIfPresent("1", (k, v) -> k + v); // 如果有值，则使用 key + value 计算生成得值
        System.out.println(map.get("1"));

        map.remove("1", "a"); // 当 key 对应的 value 等于 "a" 时，才删除

        System.out.println(map.getOrDefault("1", "11")); // 获取不到值时使用传入的默认值

        map.merge("1", "b", (k, v) -> k + v); // 如果值存在则将 value 与 传入的 value 使用后面的函数计算后作为新值，否则设置为传入的值
        System.out.println(map.get("1"));
    }
}
