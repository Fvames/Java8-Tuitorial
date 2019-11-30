package dev.fvames.pattern.strategy;

/**
 * 火车
 */
public class Train implements IVehicle {

    private static final int mph = 120;

    public void goToCity(String cityName, Double km) {
        System.out.println(">>> 火车");
        System.out.println("身份证取票提前 30 分钟检票进站，有座位及卧铺");
        System.out.println("时速 " + mph + " Km/h，" + getTime(km) + " 个小时到达" + cityName);
    }

    private Double getTime(Double km) {
        return km / mph;
    }
}
