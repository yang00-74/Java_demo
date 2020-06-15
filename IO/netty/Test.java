package netty;

/**
 * @author nathan.yang
 * @date 2020/6/15
 */
public class Test {

    public static void main(String[] args) throws Exception {
        NettyServer.start();
        Thread.sleep(100);

        NettyClient client = NettyClient.start();
        while (client.sendMsg(/*new Scanner(System.in).nextLine()*/"good..."));
    }

}
