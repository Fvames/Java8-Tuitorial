package dev.fvames.design.design014.myself;

/**
 * Created by James on 2018/8/13.
 */
public class RealData implements Data {

    private String result;

    @Override
    public String getRequest() {
        return result;
    }

    public RealData(String queryData) {
        System.out.println("根据" + queryData + "进行查询，这是一个很耗时的操作..");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完毕，获取结果");
        result = "****真实的数据： {数据key： value}";
    }
}
