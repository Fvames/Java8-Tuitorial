package dev.fvames.pattern.template;

public class PcComputer extends MakeComputer {

    @Override
    protected boolean needPack() {
        return true;
    }

    @Override
    void make() {
        System.out.println("制作 PC 机");
    }
}
