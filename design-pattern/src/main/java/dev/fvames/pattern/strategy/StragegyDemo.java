package dev.fvames.pattern.strategy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by James on 2018/11/23.
 */
public class StragegyDemo {
    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.addItem(new Item("IPAD", 50000));
        bill.addItem(new Item("iPhone", 800000));

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.start();

        //bill.pay(PayMethodFactory.getPayMethod("debit"));
        // 整合 spring，避免新增策略时增加 case
        bill.pay(Card.payMethodMap.get("debit"));
    }
}
