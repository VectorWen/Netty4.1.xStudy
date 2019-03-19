package com.vector.netty.test5.object;

import com.vector.netty.SimpleServer;

/**
 * @author: vector.huang
 * @date: 2019/03/19 12:37
 */
public class SendObjectServer {

    private static long userId = 0;

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            userId++;
            //当有连接连接进来的时候，把handler 添加进去
            ch.pipeline()
                    .addLast(new SendObjectEncoder())
                    .addLast(new SendObjectHandler(userId));

        });
    }

}
