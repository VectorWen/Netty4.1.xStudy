package com.vector.netty.test5.object;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: vector.huang
 * @date: 2019/03/19 12:41
 */
public class SendObjectHandler extends ChannelInboundHandlerAdapter {

    private long userId;

    public SendObjectHandler(long userId) {
        this.userId = userId;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        User user = new User();
        user.setId(userId);
        user.setName("中国人");
        user.setAvatar("http://sfasfas/afsf/asfas");
        ctx.writeAndFlush(user).sync();

        ctx.close();
    }
}
