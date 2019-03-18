package com.vector.netty.test2.string;

import com.vector.netty.SimpleServer;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author: vector.huang
 * @date: 2019/03/18 13:09
 */
public class StringServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080,ch->{
            ch.pipeline()
                    //接受到内容后，解码为String
                    .addLast(new StringDecoder())
                    //打印String 的Handler
                    .addLast(new StringServerHandler());

        });
    }

}
