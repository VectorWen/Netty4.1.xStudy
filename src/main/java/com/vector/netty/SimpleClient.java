package com.vector.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:50
 */
public class SimpleClient {

    public static void run(String host, int port, InitChannel initChannel) throws Exception {

        //客户端只有一个异步线程池
        var worker = new NioEventLoopGroup();

        try {
            //客户端中，把ServerBootstrap 改为Bootstrap
            var boot = new Bootstrap();
            boot.group(worker);

            //也是异步处理Channel
            boot.channel(NioSocketChannel.class);

            //连接成功后，添加处理器
            boot.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    initChannel.initChannel(ch);
                }
            });

            //保持连接，相当于固定时间内认为还连接着
            boot.option(ChannelOption.SO_KEEPALIVE, true);

            //连接
            ChannelFuture f = boot.connect(host, port).sync();

            //连接成功后，开始工作
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

}
