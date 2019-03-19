package com.vector.netty.test4.message;

import com.vector.netty.SimpleServer;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:33
 */
public class LoopWriteTimeServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            //当有连接连接进来的时候，把handler 添加进去
            ch.pipeline().addLast(new LoopWriteTimeHandler());
        });
    }

}
