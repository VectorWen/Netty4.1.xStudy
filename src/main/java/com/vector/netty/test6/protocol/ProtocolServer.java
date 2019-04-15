package com.vector.netty.test6.protocol;

import com.vector.netty.SimpleServer;
import com.vector.netty.test5.object.SendObjectEncoder;
import com.vector.netty.test5.object.SendObjectHandler;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @author: vector.huang
 * @date: 2019/03/22 09:09
 */
public class ProtocolServer {

    public static void main(String[] args) throws Exception {
        SimpleServer.run(8080, ch -> {
            //当有连接连接进来的时候，把handler 添加进去
            ch.pipeline()
                    .addLast(new FixedLengthFrameDecoder(4))
                    .addLast(new SendObjectHandler(0));

        });
    }


}
