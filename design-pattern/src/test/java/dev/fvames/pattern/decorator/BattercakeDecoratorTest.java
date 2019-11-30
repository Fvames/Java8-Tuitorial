package dev.fvames.pattern.decorator;

public class BattercakeDecoratorTest {

    public static void main(String[] args) {
        // 基础班
        Battercake battercake = new BaseBattercake();
        // 加个蛋
        battercake = new EggDecorator(battercake);
        ((EggDecorator) battercake).addServer();
        // 加个香肠
        battercake = new SauageDecorator(battercake);
        ((SauageDecorator) battercake).addServer();

        System.out.println(battercake.getMsg() + ", 总价： " + battercake.getPrice());
    }
}
