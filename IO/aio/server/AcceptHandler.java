package aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

    private static int CLIENT_COUNT = 0;

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler attachment) {
        //继续接受其他客户端的请求
        CLIENT_COUNT++;
        System.out.println("Server: client num " + CLIENT_COUNT);
        attachment.serverSocketChannel.accept(attachment, this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读  第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
