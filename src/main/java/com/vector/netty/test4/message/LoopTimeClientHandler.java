package com.vector.netty.test4.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: vector.huang
 * @date: 2019/03/19 10:12
 */
public class LoopTimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        long time = (long) msg;

        //打印出来
        LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        var timeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(timeStr);

    }
}
