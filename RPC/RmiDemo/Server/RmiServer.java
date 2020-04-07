package RPC.RmiDemo.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author Ye.Yang
 * @date 19-8-7 下午5:33
 * <p>
 * 服务端server启动, Rmi为 JDK 的RPC框架
 **/
public class RmiServer {

    public static void main(String[] args) {
        try {
            RmiService service = new RmiServiceImpl();
            //在本地创建和暴露一个注册服务实例，端口为9999
            LocateRegistry.createRegistry(9999);
            //注册service服务到上面创建的注册实例上
            Naming.rebind("rmi://127.0.0.1:9999/service1", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------------server start-----------------");
    }
}
