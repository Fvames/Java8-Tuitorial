package dev.fvames.pattern.adapter;

public class PowerAdapter implements DC5 {
    private AC22O ac22O;

    public PowerAdapter(AC22O ac22O) {
        this.ac22O = ac22O;
    }

    public int outputDC5V() {
        int ac220v = ac22O.outputAC220V();
        int dc5 = ac220v / 44;
        System.out.println("使用变压器输入 AC : " + dc5 + " V" + " , 输出 DC " + dc5 + " V");
        return dc5;
    }
}
