package dev.fvames.design.design014.myself;

/**
 * Created by James on 2018/8/13.
 */
public class FutureData implements Data {


    private boolean isReady = false;
    private RealData realData;

    @Override
    public synchronized String getRequest() {

        if (!isReady) {
            // 如果没有设置好数据，则一直阻塞
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData.getRequest();
    }


    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }

        this.realData = realData;

        isReady = true;
        System.out.println("-------设置真实数据，并唤醒getRequest()");
        notify();
    }
}
