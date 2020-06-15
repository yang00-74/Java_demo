package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @author nathan.yang
 * @date 2020/6/15
 */

public class ClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 要发送的信息
        System.out.println("client channel active... ");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        try {
            // 用于获取客户端发来的数据信息
            System.out.println("client receive:" + msg.toString());
        } finally {
            // 写(write)数据, msg 引用将被自动释放不用手动处理; 但只读数据时, 必须手动释放引用数
            ReferenceCountUtil.release(msg);
        }
    }

}
