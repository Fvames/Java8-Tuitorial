package dev.fvames.design.design014.myself;

/**
 * Created by James on 2018/8/13.
 */
public class FutureClient {


    public Data request(String queryData) {
        FutureData data = new FutureData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("--------查询设置真实数据-------");
                RealData realData = new RealData(queryData);
                data.setRealData(realData);
            }
        }).start();

        return data;
    }
}
