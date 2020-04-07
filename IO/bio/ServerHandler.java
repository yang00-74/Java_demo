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
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStreamReader inputStreamReader;
        BufferedReader in;
        PrintWriter out;
        try {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(inputStreamReader);
            out = new PrintWriter(socket.getOutputStream(), true);
            String exp;
            while (null != (exp = in.readLine())) {
                // 通过BufferedReader读取一行，如果已经读到输入流尾部或者对端关闭，返回null,退出循环
                System.out.println("Server: receive " + exp);
                String result = "Server: send " + exp;
                out.println(result);
            }
            System.out.println("Server: handler quit");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != socket)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
