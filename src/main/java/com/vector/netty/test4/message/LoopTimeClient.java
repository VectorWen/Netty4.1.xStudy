package com.vector.netty.test4.message;

import com.vector.netty.SimpleClient;

/**
 * @author: vector.huang
 * @date: 2019/03/19 10:14
 */
public class LoopTimeClient {


    public static void main(String[] args) throws Exception {
        SimpleClient.run("127.0.0.1", 8080, ch -> {
            ch.pipeline().addLast(new LoopTimeDecoder())
                    .addLast(new LoopTimeClientHandler());
        });
    }


}
