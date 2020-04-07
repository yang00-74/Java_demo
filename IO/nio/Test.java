package nio;

import java.util.Scanner;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Test {


    public static void main(String[] args) throws Exception {
        new Thread(Server::start).start();
        //避免客户端先于服务器启动前执行®代码
        Thread.sleep(100);

        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine()));
    }

}
