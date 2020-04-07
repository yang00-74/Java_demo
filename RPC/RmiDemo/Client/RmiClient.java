package RPC.RmiDemo.Client;

import RPC.RmiDemo.Server.RmiService;

import java.rmi.Naming;

/**
 * @author Ye.Yang
 * @date 19-8-7 下午5:35
 * <p>
 * 客户端调用rmi服务
 **/
public class RmiClient {


    public static void main(String[] args) {
        try {
            // 根据注册的服务地址来查找服务，然后就可以调用API对应的方法了
            RmiService service = (RmiService) Naming.lookup("rmi://localhost:9999/service1");
            System.out.println(service.hello("RMI"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
