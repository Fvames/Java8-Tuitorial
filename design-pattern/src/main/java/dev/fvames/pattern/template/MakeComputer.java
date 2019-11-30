package dev.fvames.pattern.template;

public abstract class MakeComputer {

    protected final void makeComputer() {
        prepareMaterial();
        make();

        if (needPack()) {
            pack();
        }
    }

    protected void pack() {
        System.out.println(">>>>> 打包");
    }

    protected boolean needPack() {
        return false;
    }

    abstract void make();

    private void prepareMaterial() {
        System.out.println(">>>> 准备材料");
    }
}
