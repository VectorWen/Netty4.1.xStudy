package com.vector.netty.test4.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: vector.huang
 * @date: 2019/03/19 10:11
 */
public class LoopTimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        //读取了两个long 才算一个消息，不然不读取。
        if (in.readableBytes() > 16) {
            out.add(in.readLong());
            out.add(in.readLong());
        }

    }
}
