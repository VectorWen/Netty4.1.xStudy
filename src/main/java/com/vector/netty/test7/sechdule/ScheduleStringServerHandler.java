package com.vector.netty.test7.sechdule;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:10
 */
@ChannelHandler.Sharable
public class ScheduleStringServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //已经被StringDecoder 解码成字符串了，所以可以强转型
        String msgStr = (String) msg;
        System.out.println(msgStr);

        ctx.channel().eventLoop().schedule(() -> {
            System.out.println(System.currentTimeMillis());
        }, 1, TimeUnit.SECONDS);


    }
}
