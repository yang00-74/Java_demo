package aio;

import aio.client.Client;
import aio.server.Server;

import java.util.Scanner;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class Test {


    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        System.out.println("please input:");
        Scanner scanner = new Scanner(System.in);
        while (Client.sendMsg(scanner.nextLine()));
        scanner.close();
    }

}
