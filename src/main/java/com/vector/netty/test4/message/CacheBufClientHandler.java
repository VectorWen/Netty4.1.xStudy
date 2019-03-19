package com.vector.netty.test4.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: vector.huang
 * @date: 2019/03/19 09:03
 */
public class CacheBufClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 每一个连接会new 一个这个Handler，所以这个buf 是多个的
     * 不会出现多个连接写同一个buf
     */
    private ByteBuf buf;

    /**
     * 生命周期的开始
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        buf = ctx.alloc().buffer(16);
    }

    /**
     * 生命周期的结束
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        buf.release();
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //读取传递过来的数据
        ByteBuf m = (ByteBuf) msg;
        //缓存起来
        buf.writeBytes(m);
        //回收
        m.release();

        //把两个时间戳当成一条消息，所以只有字节数大于16才会处理
        if (buf.readableBytes() >= 16) {

            long time = buf.readLong();
            //打印出来
            LocalDateTime now = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
            var timeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
            System.out.print(timeStr);
            System.out.print(" -- ");

            time = buf.readLong();
            //打印出来
            now = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
            timeStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
            System.out.println(timeStr);

            //丢弃已经读过的字节
            //读了的字节就不要了
            buf.discardReadBytes();
        }

    }
}
