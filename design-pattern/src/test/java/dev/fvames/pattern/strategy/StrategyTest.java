package dev.fvames.pattern.strategy;

public class StrategyTest {

    public static void main(String[] args) {
        WebSite12306.getVehicle(WebSite12306.VehicleOption.HIGH_SPEED_RAIL).goToCity("成都", 520.30);
        WebSite12306.getVehicle(WebSite12306.VehicleOption.TRAIN).goToCity("北京", 980.70);
    }
}
