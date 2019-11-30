package dev.fvames.pattern.decorator;

public class SauageDecorator extends BattercakeDecorator {


    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "加1跟香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }

    protected void addServer() {
        System.out.println("准备香肠");
    }
}
