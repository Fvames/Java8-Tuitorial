package com.james.juc.thread.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetWorkServer implements Runnable {

    private final ExecutorService pool;
    private final ServerSocket socket;

    public NetWorkServer(int serverPort, int nThreads) throws IOException {
        pool = Executors.newFixedThreadPool(nThreads);
        socket = new ServerSocket(serverPort);
    }

    public ExecutorService getPool() {
        return pool;
    }

    @Override
    public void run() {
        try {
            while (true) {

                pool.execute(new handle(socket.accept()));
            }
        } catch (IOException e) {
            pool.shutdown();
        }
    }

    class handle implements Runnable {

        private final Socket socket;

        public handle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("socket: " + socket);
        }
    }

}
