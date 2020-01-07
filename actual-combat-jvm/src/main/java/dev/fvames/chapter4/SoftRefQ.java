package dev.fvames.chapter4;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftRefQ {
    static ReferenceQueue<User> softQueue = null;

    public static class CheckRefQueue extends Thread {

        @Override
        public void run() {
            while (true) {
                if (null != softQueue) {
                    UserSoftReference obj = null;
                    try {
                        obj = (UserSoftReference) softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (null != obj) {
                        System.out.println("user id " + obj.uid + " is delete");
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {

        int uid;

        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            uid = referent.id;
        }
    }

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();

        User user = new User(1, "LSJ");
        softQueue = new ReferenceQueue<>();

        UserSoftReference userSoftRef = new UserSoftReference(user, softQueue);
        user = null;
        System.out.println(userSoftRef.get());
        System.gc();

        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        byte[] b = new byte[7 * 969 * 1024];
        // 堆空间不足，GC 回收软件用
        System.gc();
        System.out.println(userSoftRef.get()); // null
    }
}
