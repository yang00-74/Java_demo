package aio.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author nathan.yang
 * @date 2020/3/18
 */
public class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public ClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String body;
        body = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Client: receive " + body);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("Client: receive fail");
        try {
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

}
