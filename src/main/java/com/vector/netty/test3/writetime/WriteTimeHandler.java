package com.vector.netty.test3.writetime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:34
 */
public class WriteTimeHandler extends ChannelInboundHandlerAdapter {

    /**
     * Channel 连接成功后，这个时候已经可以开始写数据了
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //分配一个ByteBuf，用来存储时间戳
        final var buf = ctx.alloc().buffer(8);
        buf.writeLong(System.currentTimeMillis());

        //发送给Client
        //把buf 交给别人，自己别管了
        final var f = ctx.writeAndFlush(buf);

        //等待写完
        f.addListener((future) -> {
            //写完之后直接关闭就好了
            ctx.close();
        });

    }
}
