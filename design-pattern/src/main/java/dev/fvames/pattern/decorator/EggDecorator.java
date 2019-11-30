package dev.fvames.pattern.decorator;

public class EggDecorator extends BattercakeDecorator {


    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 1;
    }

    protected void addServer() {
        System.out.println("准备鸡蛋");
    }
}
