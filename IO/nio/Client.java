package nio;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Client {

    private static String DEFAULT_HOST = "127.0.0.1";

    private static int DEFAULT_PORT = 10086;

    private static String QUIT_MSG = "q";

    private static ClientHandler clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null) {
            return;
        }
        clientHandle = new ClientHandler(ip, port);
        new Thread(clientHandle, "Client").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception {
        if (QUIT_MSG.equals(msg)) {
            System.out.println("Client: exit");
            clientHandle.stop();
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }

}
