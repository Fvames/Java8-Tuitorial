package dev.fvames.pattern.proxy;

public class CEOLeader implements ILeader {

    public void sign(String name) {
        System.out.println("CEO: " + name + "agree sign name");
    }
}
