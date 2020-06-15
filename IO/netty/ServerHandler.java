package netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author nathan.yang
 * @date 2020/6/15
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    public static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        // 用于获取客户端发来的数据信息
        System.out.println("Server receive:" + msg.toString());
        if ("exit".equals(msg)) {
            ctx.close();
            System.out.println("连接关闭");
            return;
        }
        // 写数据给客户端
        String str = "Hi I am Server ...";
        // 消息以换行符结尾
        ctx.writeAndFlush(str + "\r\n");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        GROUP.writeAndFlush("服务器： " + channel.remoteAddress() + "加入");
        GROUP.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        GROUP.writeAndFlush("服务器 ： " + channel.remoteAddress() + "离开");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + " 下线");
    }

}
