package dev.fvames.pattern.proxy;

public class CglibProxyTest {

    public static void main(String[] args) {
        CEOLeader ceo = new CEOLeader();
        ILeader leader = (ILeader) new CglibProxy().getInstance(CEOLeader.class);
        leader.sign("二爷");
    }
}
