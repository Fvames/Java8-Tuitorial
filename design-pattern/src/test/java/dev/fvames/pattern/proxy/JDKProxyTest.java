package dev.fvames.pattern.proxy;

public class JDKProxyTest {
    public static void main(String[] args) {

        CEOLeader ceoLeader = new CEOLeader();
        ILeader iLeader = (ILeader) new JDKProxy().getInstance(ceoLeader);
        iLeader.sign("鸣佐");
    }

}
