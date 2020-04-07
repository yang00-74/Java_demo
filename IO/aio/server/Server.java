package aio.server;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Server {

    private static int DEFAULT_PORT = 10086;
    private static AsyncServerHandler SERVER_HANDLER;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (SERVER_HANDLER != null) {
            System.out.println("Server: already start");
            return;
        }
        SERVER_HANDLER = new AsyncServerHandler(port);
        new Thread(SERVER_HANDLER, "Server").start();
    }

}
