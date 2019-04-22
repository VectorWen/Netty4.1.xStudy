package com.vector.netty.test8.idle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;

/**
 * @author: vector.huang
 * @date: 2019/04/22 17:42
 */
public class IdleClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接成功");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        if (buf.readableBytes() > 5) {
            throw new RuntimeException("内容太长了");
        }

        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("读取了数据：" + msg);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生了异常");
        cause.printStackTrace();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("收到了userEvent: " + evt);

        if (evt instanceof IdleStateEvent) {
            System.out.println("因为空闲太久，赶紧发个心跳");
            ByteBuf byteBuf = Unpooled.directBuffer();
            byteBuf.writeCharSequence("xt", Charset.defaultCharset());
            ctx.writeAndFlush(byteBuf);
        }
    }
}
