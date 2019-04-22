package com.vector.netty.test8.idle;

import com.vector.netty.SimpleClient;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: vector.huang
 * @date: 2019/04/22 17:55
 */
public class IdleClient {

    public static void main(String[] args) throws Exception {
        SimpleClient.run("127.0.0.1", 8080, ch -> {
            ch.pipeline()
                    .addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS))
                    .addLast(new IdleClientHandler());
        });
    }


}
