package dev.fvames.pattern.proxy;

public class SecretaryProxyTest {

    public static void main(String[] args) {
        CEOLeader ceo = new CEOLeader();
        ILeader leader = new SecretaryProxy(ceo);
        leader.sign("secretary");
    }
}
