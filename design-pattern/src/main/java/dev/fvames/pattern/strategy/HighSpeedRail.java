package dev.fvames.pattern.strategy;

/**
 * 高铁
 */
public class HighSpeedRail implements IVehicle {

    private static final int mph = 300;

    public void goToCity(String cityName, Double km) {
        System.out.println(">>> 高铁");
        System.out.println("身份证提前 15 分钟刷票进站，只有座位");
        System.out.println("时速 " + mph + " Km/h，" + getTime(km) + " 个小时到达" + cityName);
    }

    private Double getTime(Double km) {
        return km / mph;
    }
}
