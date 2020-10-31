package dev.fvames.design.design014.myself;

/**
 * Created by James on 2018/8/13.
 */
public class Main {

    public static void main(String[] args) {
        FutureClient client = new FutureClient();

        Data data = client.request("查询数据");
        System.out.println("请求发送成功!");
        System.out.println("做其他的事情...");

        String result = data.getRequest();
        System.out.println("查询结果：" + result);
    }
}
