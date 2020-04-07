package aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    //用于读取半包消息和发送应答
    private AsynchronousSocketChannel channel;

    public ReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;

    }

    //读取到消息后的处理
    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        //flip操作
        buffer.flip();
        byte[] message = new byte[buffer.remaining()];
        buffer.get(message);
        String expr = new String(message, StandardCharsets.UTF_8);
        System.out.println("Server: receive " + expr);
        String resultMsg;
        try {
            resultMsg = "Server: send " + expr;
        } catch (Exception e) {
            resultMsg = "Error:" + e.getMessage();
        }
        //向客户端发送消息
        doWrite(resultMsg);
    }

    //发送消息
    private void doWrite(String result) {
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        //异步写数据 参数与前面的read一样
        channel.write(writeBuffer, writeBuffer, new WriteHandler(channel));
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
