package com.james.java8.samples.lambda;

/**
 * Created by James on 2018/2/21.
 */
public class Interface {
    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(positive(a));
        }

        static int positive(int a) {
            return a > 0 ? a : 0;
        }
    }


    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100)); // 100.0
        System.out.println(formula.sqrt(-2));       // 0.0
        System.out.println(Formula.positive(-4));   // 0
    }
}
