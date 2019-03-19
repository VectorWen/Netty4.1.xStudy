package com.vector.netty.test5.object;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: vector.huang
 * @date: 2019/03/19 12:44
 */
public class SendObjectDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        User user = new User();
        user.setId(in.readLong());
        int length = in.readInt();
        user.setName(String.valueOf(in.readCharSequence(length, Charset.defaultCharset())));
        length = in.readInt();
        user.setAvatar(String.valueOf(in.readCharSequence(length,Charset.defaultCharset())));
        out.add(user);

    }
}
