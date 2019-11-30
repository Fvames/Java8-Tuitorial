package dev.fvames.pattern.adapter;

public class AdapterTest {

    public static void main(String[] args) {

        DC5 dc5 = new PowerAdapter(new AC22O());
        dc5.outputDC5V();
    }
}
