package com.vector.netty.test2.string;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:10
 */
public class StringServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //已经被StringDecoder 解码成字符串了，所以可以强转型
        String msgStr = (String) msg;
        System.out.println(msgStr);

    }
}
