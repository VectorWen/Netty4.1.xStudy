package com.vector.netty.test3.writetime;

import com.vector.netty.SimpleClient;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:56
 */
public class WriteTimeClient {

    public static void main(String[] args) throws Exception {
        SimpleClient.run("127.0.0.1", 8080, ch -> {
            ch.pipeline().addLast(new WriteTimeClientHandler());
        });
    }

}
