package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */

public class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 10086;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    private static String QUIT_MSG = "q";

    private static Socket socket;

    public static void start() {
        try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            System.out.println("Client: start, port " + DEFAULT_SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean send(String exp) {
        if (QUIT_MSG.equals(exp)) {
            System.out.println("Client: quit");
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
        System.out.println("Client: send " + exp);
        InputStreamReader inputStreamReader;
        BufferedReader in;
        PrintWriter out;
        try {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(inputStreamReader);
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(exp);
            System.out.println("Client: receive " + in.readLine());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
