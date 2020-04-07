package nio;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Server {
    private static int DEFAULT_PORT = 10086;
    private static ServerHandler serverHandle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null) {
            return;
        }
        serverHandle = new ServerHandler(port);
        new Thread(serverHandle, "Server").start();
    }

}
