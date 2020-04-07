package RPC.MyRPC.Server;

import RPC.MyRPC.Common.ServiceProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ye.Yang
 * @date 19-8-8 上午10:21
 * <p>
 * 服务端 remote 层,负责开启服务
 **/
public class ServerRemoter {

    private static final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void startServer(int port) throws Exception {

        final ServerSocket server = new ServerSocket();
        // 绑定服务地址
        server.bind(new InetSocketAddress(port));
        System.out.println("-----------start server----------------");
        try {
            while (true) {
                final Socket socket = server.accept();
                executor.execute(new MyRunnable(socket));
            }
        } finally {
            server.close();
        }
    }

    class MyRunnable implements Runnable {

        private Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                //1.接收参数
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ServiceProtocol.ProtocolModel model = (ServiceProtocol.ProtocolModel) inputStream.readObject();

                //服务端执行Impl的方法
                Object object = ServiceProcessor.getInstance().process(model);

                //返回结果给客户端
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(object);
                outputStream.flush();
                //5.关闭连接
                outputStream.close();
                inputStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
