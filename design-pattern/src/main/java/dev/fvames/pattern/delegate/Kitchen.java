package dev.fvames.pattern.delegate;


import java.util.HashMap;
import java.util.Map;

/**
 * 资源持有者，任务分发
 */
public class Kitchen implements Ikitchen {

    private Map<String, Ikitchen> info = new HashMap<String, Ikitchen>();
    public static final String ROASTER = "ROASTER";
    public static final String RICECOOK = "RICECOOK";


    public Kitchen() {
        info.put(ROASTER, new Roaster());
        info.put(RICECOOK, new RiceCook());
    }

    public void makeFood(String option) {
        info.get(option).makeFood(option);
    }
}
