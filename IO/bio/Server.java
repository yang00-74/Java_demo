package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Server {

    //默认的端口号
    private static int DEFAULT_PORT = 10086;
    private static ServerSocket server;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) {
        if (server != null) return;
        new Thread(() -> {
            try {
                // 通过构造函数创建ServerSocket，如果端口合法且空闲，服务端就监听成功
                server = new ServerSocket(port);
                System.out.println("Server: start, port " + port);
                // 无限循环监听客户端连接，如果没有客户端接入，将阻塞在accept操作上
                while (true) {
                    Socket socket = server.accept();
                    //当有新的客户端接入时，会执行下面的代码，创建一个新的线程处理这条 Socket 连接
                    new Thread(new ServerHandler(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //一些必要的清理工作
                if (server != null) {
                    System.out.println("Server: close");
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server = null;
                }
            }
        }, "Server").start();


    }
}
