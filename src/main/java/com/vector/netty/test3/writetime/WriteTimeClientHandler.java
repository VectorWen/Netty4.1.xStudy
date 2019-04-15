package com.vector.netty.test3.writetime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:57
 */
public class WriteTimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        //读取时间
        long time = msg.readLong();

        //打印出来
        LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        var timeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(timeStr);

        //不是自己读取了ByteBuf，是SimpleChannelInboundHandler 读取了
        //不需要自己调用release 回收
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //完成了这次读取
        ctx.close();
    }
}
