package netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @author nathan.yang
 * @date 2020/6/15
 */
public class NettyServer {

    private static final int DEFAULT_PORT = 10086;

    public static void start() {
        new Thread(() -> {
            // 创建监听线程组, 监听客户端请求
            NioEventLoopGroup boosGroup = new NioEventLoopGroup();
            // 创建工作线程组, 处理请求
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 服务器辅助启动类配置
            serverBootstrap.group(boosGroup, workerGroup)
                    // 设置 channel 类型为NIO类型
                    .channel(NioServerSocketChannel.class)
                    // 设置连接配置参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 配置入站、出站事件handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 配置入站、出站事件channel
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                    .addLast(new ServerHandler());
                        }
                    });

            // 绑定端口
            serverBootstrap.bind(DEFAULT_PORT).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 端口[" + DEFAULT_PORT + "]绑定成功!");
                } else {
                    System.err.println("端口[" + DEFAULT_PORT + "]绑定失败!");
                }
            });
        }, "Server").start();
    }
}
