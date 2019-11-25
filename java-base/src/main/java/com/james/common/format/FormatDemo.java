package com.james.common.format;

import java.util.Formatter;

/**
 * <p>
 * format字符串的格式化参数语法如下：  %[argument_index$][flags][width][.precision]conversion
 * <ul>
 * <li>argument_index$： 表明参数在参数列表中的位置。第一个参数由 「1$」引用</li>
 * <li>flags：修改输出格式的字符集，flag的使用依赖于conversion。「'-'，'#'，'+'，'  '，'0'，','，'('」，常用的是'-'：将结果设为左对齐 </li>
 * <li>width：控制一个域的最小值，默认情况下下是右对齐的，可以通过使用“-”标志来改变对其方向</li>
 * <li>precision：精度，用于String时，表示输出字符的最大数量，用于浮点数时，表示小数部分要显示出来的 位数（默认是6位），多则舍入，少则补0，用于整数会触发异常</li>
 * <li>conversion：转换格式，可选的格式有：d 整数型（十进制） c Unicode字符 b Boolean值 s String f 浮点数（十进制） e 浮点数（科学计数） x 整数（十六进制） h 散列码 % 字符串“%”。（boolean 类型时 0也会转换为 true）</li>
 * </ul>
 * </p>
 *
 * @version 2019/6/4 10:59
 */

public class FormatDemo {

    public static void main(String[] args) {

        formatHead();
        printData();
        System.out.printf("This is %s，%d \n", "Hello World", 4);
    }


    public static void formatHead() {
        Formatter f = new Formatter(System.out);
        // 15 空格，右对齐，字符
        f.format("%15s %5s %5s \n", "username", "level", "score");
        f.format("%-10s %5s %5s \n", "---", "---", "---");
    }

    public static void printData() {
        Formatter f = new Formatter(System.out);
        f.format("%10s %5d %5.2f \n", "Jason", 1, 9.87654321);
        f.format("%-10s %5d %5.2f \n", "arthinking", 2, 9.6512);
    }

}
