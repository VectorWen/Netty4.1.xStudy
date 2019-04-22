package com.vector.netty.test7.sechdule;

import com.vector.netty.SimpleServer;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author: vector.huang
 * @date: 2019/04/16 08:57
 */
public class SechduleServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            ch.pipeline()
                    //接受到内容后，解码为String
                    .addLast(new StringDecoder())
                    //打印String 的Handler
                    .addLast(new ScheduleStringServerHandler());

        });
    }


}
