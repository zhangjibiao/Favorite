package stu.Protocol;


import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.io.Serializable;

public abstract class Msg implements Serializable {
    public MsgType msgType;
    public abstract void handle(ChannelHandlerContext ctx);
}
