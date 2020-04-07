package aio.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class AsyncServerHandler implements Runnable {
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel serverSocketChannel;

    public AsyncServerHandler(int port) {
        try {
            //创建服务端通道
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("Server: start, port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // CountDownLatch 初始化
        // 作用：在完成一组正在执行的操作之前，允许当前的线程一直阻塞,防止服务端执行完成后退出, 也可以使用 while(true) + sleep
        latch = new CountDownLatch(1);
        //用于接收客户端的连接
        serverSocketChannel.accept(this, new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
