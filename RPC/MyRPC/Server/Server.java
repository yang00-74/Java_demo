package RPC.MyRPC.Server;

import RPC.MyRPC.Common.RpcService;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午10:56
 *
 * 服务端发布接口,启动服务
 **/
public class Server {

    public static void main(String[] args) throws Exception {

        // 发布接口
        ServiceProcessor.getInstance().publish(RpcService.class, new RpcServiceImpl());

        // 启动server
        ServerRemoter remoter = new ServerRemoter();
        remoter.startServer(9999);
    }
}
