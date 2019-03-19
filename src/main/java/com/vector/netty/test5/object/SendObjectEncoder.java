package com.vector.netty.test5.object;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: vector.huang
 * @date: 2019/03/19 12:38
 */
public class SendObjectEncoder extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {

        out.writeLong(msg.getId());
        byte[] name = msg.getName().getBytes();
        out.writeInt(name.length);
        out.writeBytes(name);
        byte[] avatar = msg.getAvatar().getBytes();
        out.writeInt(avatar.length);
        out.writeBytes(avatar);

    }
}
