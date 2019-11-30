package dev.fvames.pattern.template;

public class MakeComputerTest {

    public static void main(String[] args) {
        MacComputer macComputer = new MacComputer();
        macComputer.makeComputer();


        PcComputer pcComputer = new PcComputer();
        pcComputer.makeComputer();
    }
}
