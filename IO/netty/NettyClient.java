package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * @author nathan.yang
 * @date 2020/6/15
 */
public class NettyClient {

    private Channel channel;

    public static NettyClient start() throws Exception {
        NettyClient client = new NettyClient();
        client.connect(10086, "127.0.0.1");
        return client;
    }

    /**
     * 连接服务器
     *
     * @param port
     * @param host
     * @throws Exception
     */
    public void connect(int port, String host) {
        new Thread(() -> {
            // 配置客户端NIO线程组
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                // 客户端辅助启动类 对客户端配置
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                ch.pipeline()
                                        // 基于行的解码器，接收的数据需要以换行符为结束，否则不会读取到
                                        .addLast(new LineBasedFrameDecoder(1024))
                                        .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                        .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                        // 处理网络IO
                                        .addLast(new ClientHandler());
                            }
                        });
                // 异步链接服务器 同步等待链接成功
                ChannelFuture f = b.connect(host, port).sync();
                channel = f.channel();
                // 等待链接关闭
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
                System.out.println("客户端释放了线程资源...");
            }
        }, "Client").start();

    }

    public boolean sendMsg(String msg) {
        if ("q".equals(msg)) {
            return false;
        }
        System.out.println("Client send:" + msg);
        if (null == channel) {
            return true;
        }
        // 消息以换行符结尾
        channel.writeAndFlush(msg + "\r\n");
        return true;
    }

}
