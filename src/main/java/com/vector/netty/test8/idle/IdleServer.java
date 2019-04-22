package com.vector.netty.test8.idle;

import com.vector.netty.SimpleServer;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: vector.huang
 * @date: 2019/04/22 17:35
 */
public class IdleServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            //当有连接连接进来的时候，把handler 添加进去
            ch.pipeline()
                    //为了能快速看到效果，所以，设置为短一些
                    .addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS))
                    .addLast(new IdleServerHandler());

        });

    }

}
