package RPC.RmiDemo.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Ye.Yang
 * @date 19-8-7 下午5:27
 * <p>
 * 接口必须继承RMI的Remote
 **/
public interface RmiService extends Remote {

    // 必须有RemoteException，才是RMI方法
    String hello(String name) throws RemoteException;
}
