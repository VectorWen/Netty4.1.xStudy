package com.vector.netty.test3.writetime;

import com.vector.netty.SimpleServer;
import com.vector.netty.test1.discard.DiscardServerHandler;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:33
 */
public class WriteTimeServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            //当有连接连接进来的时候，把handler 添加进去
            ch.pipeline().addLast(new WriteTimeHandler());
        });
    }

}
