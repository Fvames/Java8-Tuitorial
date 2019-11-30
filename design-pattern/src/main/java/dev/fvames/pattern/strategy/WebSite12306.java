package dev.fvames.pattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 12306 购票网站
 */
public class WebSite12306 {

    private static final Map<String, IVehicle> vehicleMap = new HashMap<String, IVehicle>();

    static {
        vehicleMap.put(VehicleOption.HIGH_SPEED_RAIL, new HighSpeedRail());
        vehicleMap.put(VehicleOption.TRAIN, new Train());
    }

    private WebSite12306() {
    }

    public static final IVehicle getVehicle(String option) {
        // todo 默认交通工具
        return vehicleMap.get(option);
    }

    public interface VehicleOption {
        String HIGH_SPEED_RAIL = "high-speed-rail";
        String TRAIN = "train";
    }
}
