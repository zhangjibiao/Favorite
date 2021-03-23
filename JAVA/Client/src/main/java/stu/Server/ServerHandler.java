package stu.Server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import stu.Protocol.FileMsg;
import stu.Protocol.Msg;

import java.io.*;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Msg) {
            ((Msg) msg).handle(ctx);
        }
    }//以为他写的是垃圾，没想到小丑竟是我自己

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause.getMessage().equals("远程主机强迫关闭了一个现有的连接。")){
            System.out.println("客户端强行关闭了一个已有的连接");
            System.out.println("-------------------------------\n");
        }else{
            cause.printStackTrace();
        }
        ctx.close();
    }
}
