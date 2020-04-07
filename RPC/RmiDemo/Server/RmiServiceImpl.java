package RPC.RmiDemo.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Ye.Yang
 * @date 19-8-7 下午5:32
 * <p>
 * UnicastRemoteObject会生成一个代理proxy
 **/

public class RmiServiceImpl extends UnicastRemoteObject implements RmiService {

    public RmiServiceImpl() throws RemoteException {
    }

    public String hello(String name) throws RemoteException {
        System.out.println("RmiService invoke hello");
        return "Hello " + name;
    }
}
