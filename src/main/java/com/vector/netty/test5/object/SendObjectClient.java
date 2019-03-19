package com.vector.netty.test5.object;

import com.vector.netty.SimpleClient;

/**
 * @author: vector.huang
 * @date: 2019/03/19 12:47
 */
public class SendObjectClient {


    public static void main(String[] args) throws Exception {
        SimpleClient.run("127.0.0.1", 8080, ch -> {
            ch.pipeline().addLast(new SendObjectDecoder())
                    .addLast(new SendObjectClientHandler());
        });
    }


}
