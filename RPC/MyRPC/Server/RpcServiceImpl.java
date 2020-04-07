package RPC.MyRPC.Server;

import RPC.MyRPC.Common.RpcService;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午9:50
 *
 * 服务端服务实现
 **/
public class RpcServiceImpl implements RpcService {
    @Override
    public String sayHi(String name) {
        return "Hello," + name;
    }
}
