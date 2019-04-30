package com.james.java8.samples.stream;

import com.james.java8.samples.model.ModelDataGenerateUtils;
import com.james.java8.samples.model.User;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Predicate、Consumer、
 * Stream API 可以更方便的对集合进行操作，同时支持并行 parallel 处理充分利用多核 CPU
 * <p>
 * <ul>
 * Stream 流通常由三部分组成
 *
 * <li>1.数据源获取： list.stream()， Stream.of(list) </li>
 * <li>2.中间处理（懒操作）：过滤 filter()使用 {@link Stream#filter(Predicate)}，映射 map() 使用{@link Stream#map(Function)}， 排序 sorted()使用{@link Stream#sorted()} </li>
 * <li>3.结果输出（结束操作）： 返回结果值，或者输出内容 </li>
 * </ul>
 * </p>
 *
 * <p>
 * 对同一个 Stream 流多次执行操作会抛出 IllegalStateException <br>
 * Streams有一个close()方法并实现AutoCloseable <br>
 *
 * stream有两种操作：Intermediate（懒操作）和 Terminal（最终操作，只能有一个）,还有特殊的 Short-circuiting 短路操作 <br>
 * 其中，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成 <br>
 * </p>
 *
 * @version 2019/4/26 14:05
 */

public class StreamDemo1 {

    public static void main(String[] args) throws Exception {

        // 对同一个流多次操作
        //multipleOperationsOneStream();

        // 创建流
        //createStream();

        // 使用流
        //useStream();

        // 分组
        //new StreamDemo1().userGroup();

        // 对象集合
        //new StreamDemo1().userObjects();

    }

    private void userObjects() {
        List<User> users = ModelDataGenerateUtils.getUsers();

        //	-根据属性值过滤, 修改 Object 中的属性值
        users.stream().filter(user -> user.getAge() >= 30).forEach(user -> user.setUserName(user.getUserName() + user.getAge()));

        // - 返回某个属性值的List
        List<String> names = users.stream().map(User::getUserName).collect(toList());
        System.out.println("names: " + names);

        //	- 字段值排序（字符串转为 double/int 排序）
        List<User> sortByAge = users.stream().sorted(Comparator.comparingLong(User::getAge)).collect(toList());

        sortByAge.forEach(System.out::println);

    }

    private void userGroup() {
        List<User> users = ModelDataGenerateUtils.getUsers();

        Map<String, List<User>> deptUserMap = users.stream().collect(groupingBy(user -> {
            if ("中部".equals(user.getDept())) {
                return "中部";
            } else if ("南部".equals(user.getDept())) {
                return "南部";
            } else {
                return "无";
            }
        }));

        deptUserMap.forEach((key, value) -> System.out.println("key：" + key + "， value：" + value));

        System.out.println(users.stream().collect(groupingBy(User::getAge, counting())));

        Map<String, User> maxAge = users.stream().collect(
                groupingBy(User::getDept,
                        collectingAndThen(maxBy(Comparator.comparingDouble(User::getAge)), Optional::get)));
        maxAge.forEach((key, value) -> System.out.println("key：" + key + "， value：" + value));
    }

    private static void useStream() {

        // 过滤、去重、跳过、限制数量、排序、转换
        List<Integer> stream = Stream.iterate(1, i -> i + 1).limit(20).collect(Collectors.toList());
        List<Integer> list1 = stream.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .skip(2)
                .limit(5)
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());
        System.out.println("list1：" + list1);

        // 匹配、查找、返回新的数据流
        boolean hasAnyMatch = Stream.of("Java", "PHP", "C++", "C", "Python", "Go").anyMatch(s -> s.contains("o"));
        System.out.println("hasAnyMatch:" + hasAnyMatch);

        Optional<String> findAny = Stream.of("Java", "PHP", "C++", "C", "Python", "Go").findAny();
        System.out.println("findAny:" + findAny.get());

        List<String> list2 = Stream.of("Java", "PHP", "C++", "C", "Python", "Go").peek(s -> {
        }).filter(s -> s.contains("C")).collect(Collectors.toList());
        System.out.println("peek:" + list2);

        // 归约汇总
        Optional<Integer> sum = stream.stream().reduce((n, m) -> n + m);
        System.out.println("sum: " + sum.get());
        System.out.println(stream.stream().limit(20).max(Comparator.comparing(Integer::intValue)));
        System.out.println(stream.stream().count());

        System.out.println("--------------------------");
        IntSummaryStatistics intSummaryStatistics = stream.stream().collect(IntSummaryStatistics::new, IntSummaryStatistics::accept, IntSummaryStatistics::combine);
        System.out.println("intSummaryStatistics.getMax()：" + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics.getAverage()：" + intSummaryStatistics.getAverage());

    }

    private static void createStream() throws Exception {
        List<String> list = Arrays.asList("1", "2", "3");
        Stream stream = list.stream();
        Stream.of(list);
        String[] array = {"1", "2", "3"};
        Arrays.stream(array);

        // 文件流
        //Stream<String> fileStream = Files.lines(Paths.get("text.txt"), Charset.forName("UTF-8"));

        Stream.iterate(1, i -> i + 1) // 创建源
                .filter(b -> b % 2 == 0)  // 过滤
                .limit(20)                 // 限定
                .collect(Collectors.toList()) // 转换为 list
                .forEach(System.out::println); // 输出

    }

    private static void multipleOperationsOneStream() {
        List<String> list = Arrays.asList("1", "2", "3");

        //list.forEach(System.out::println);
        //list.forEach(System.out::println);

        // java.lang.IllegalStateException: stream has already been operated upon or closed
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }
}
