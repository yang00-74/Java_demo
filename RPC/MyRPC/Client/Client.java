package RPC.MyRPC.Client;

import RPC.MyRPC.Common.RpcService;
import RPC.MyRPC.Server.RpcServiceImpl;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午10:59
 *
 * 客户端调用测试
 **/
public class Client {

    public static void main(String[] args) {
        System.out.println("----------start invoke----------------");
        RpcService service = ServiceProxyClient.getInstance(RpcService.class);
        System.out.println(service.sayHi("RPC World"));
        System.out.println("----------end invoke----------------");
    }
}
