package com.james.java8.samples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        useStream();

        // 数据收集
        // 规约和汇总
        // 分组

        // 输出

        // 并行处理
    }

    private static void useStream() {
        /*

        allMatch
        anyMatch
        builder
        collect
        concat
        count
        distinct
        empty
        filter
        forEach
        forEachOrdered
        generate
        iterate
        limit
        map
        mapToDouble
        mapToInt
        mapToLong
        noneMatch
        sorted
        toArray
        Builder

        */

        // 过滤、去重、跳过、限制数量、排序、转换
        List<Integer> list1 = Stream.iterate(1, i -> i + 1).limit(20)
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

        // list、map、set 交叉处理数据



        /*

        flatMap
        flatMapToDouble
        flatMapToInt
        flatMapToLong

        peek
         */

        // reduce
        // max
        // min
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
