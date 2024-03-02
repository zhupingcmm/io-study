package com.ocbc.io.netty;

import com.ocbc.io.netty.handler.DiscardServerHandler;
import com.ocbc.io.netty.server.DiscardServer;

public class Starter {
    public static void main(String[] args) throws InterruptedException {
        new DiscardServer(9001).run();
    }
}
