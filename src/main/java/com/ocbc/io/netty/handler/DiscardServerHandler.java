package com.ocbc.io.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashSet;
import java.util.Set;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    static Set<Channel> channels = new HashSet<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        channels.forEach(channel -> {
            channel.writeAndFlush("[客户端] " + ctx.channel().remoteAddress() + "上线了");
        });

        channels.add(ctx.channel());

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       channels.forEach(channel -> {
           if (channel == ctx.channel()) {
               channel.writeAndFlush("[自己] :"  + msg);
           } else {
               channel.writeAndFlush("[客户端] " +ctx.channel().remoteAddress()+"：" + msg);
           }
       });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());

        channels.forEach(channel -> {
            channel.writeAndFlush("[客户端] " + ctx.channel().remoteAddress() + "下线了");
        });
    }

}
