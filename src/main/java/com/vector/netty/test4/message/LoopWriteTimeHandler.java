package com.vector.netty.test4.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:34
 */
public class LoopWriteTimeHandler extends ChannelInboundHandlerAdapter {

    /**
     * Channel 连接成功后，这个时候已经可以开始写数据了
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 100; i++) {
            //分配一个ByteBuf，用来存储时间戳
            final var buf = ctx.alloc().buffer(8);
            //读取当前时间数据进buf
            buf.writeLong(System.currentTimeMillis());
            //发送给Client，同步等待发送
            //记住这里，buf 已经交付出去了，由ctx 接管了，不能再使用这个buf了
            ctx.writeAndFlush(buf).sync();
            //发送成功之后清除本来有的数据，就是index = 0
            Thread.sleep(10);
        }
        ctx.close();

    }
}
