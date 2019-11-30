package dev.fvames.pattern.single;


public class HungerSingle {

    class HungerSingleClass {
    }

    private final HungerSingleClass single = new HungerSingleClass();

    public HungerSingleClass getSingle() {
        return single;
    }

    public static void main(String[] args) {
        HungerSingle single = new HungerSingle();

        HungerSingleClass singleClass1 = single.getSingle();
        HungerSingleClass singleClass2 = single.getSingle();

        System.out.println(singleClass1);
        System.out.println(singleClass2);
    }

}
