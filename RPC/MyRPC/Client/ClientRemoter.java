package RPC.MyRPC.Client;

import RPC.MyRPC.Common.ServiceProtocol;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午10:00
 **/
public class ClientRemoter {

    private static volatile ClientRemoter instance;

    private ClientRemoter() {
    }

    public static ClientRemoter getInstance() {
        if (null == instance) {
            synchronized (ClientRemoter.class) {
                if (null == instance) {
                    instance = new ClientRemoter();
                }
            }
        }
        return instance;
    }

    public Object getDataRemote(ServiceProtocol.ProtocolModel model) {

        try (Socket socket = new Socket()) {
            // socket远程连接
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            // 请求远程执行
            outputStream.writeObject(model);
            outputStream.flush();

            //接收返回结果
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object object = inputStream.readObject();

            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
