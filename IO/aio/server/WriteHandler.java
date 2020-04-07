package aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author nathan.yang
 * @date 2020/3/19
 */
public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public WriteHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        //如果没有发送完，就继续发送直到完成
        if (buffer.hasRemaining()) {
            channel.write(buffer, buffer, this);
        } else {
            //发送完毕，准备读取数据
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            //异步读  第三个参数为接收消息回调的业务 Handler
            channel.read(readBuffer, readBuffer, new ReadHandler(channel));
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            channel.close();
        } catch (IOException ignored) {
        }
    }
}
