package com.james.java8.samples.model;

import java.util.Arrays;
import java.util.List;

/**
 * model 数据生成工具类
 *
 * @version 2019/4/29 15:04
 */

public class ModelDataGenerateUtils {


    public static List<User> getUsers() {
        return Arrays.asList(
                new User(1L, "詹姆", "中部", 30L),
                new User(2L, "布雷尼", "南部", 28L),
                new User(3L, "詹德妮", "中部", 20L),
                new User(4L, "艾迪", "中部", 20L),
                new User(5L, "洋葱骑士", "南部", 45L)
        );
    }

}
