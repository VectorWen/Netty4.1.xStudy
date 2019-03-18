package com.vector.netty.test3.writetime;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:57
 */
public class WriteTimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //读取时间
        ByteBuf buf = (ByteBuf) msg;
        long time = buf.readLong();

        //打印出来
        LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        var timeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));

        System.out.println(timeStr);

        ctx.close();

    }
}
