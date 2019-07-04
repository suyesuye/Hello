package com.huayan.Client;

import com.huayan.DAO.Response;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Response resp = (Response) msg;
            System.out.println("Client : " + resp.getId() + ", " + resp.getName() + ", " + resp.getResponseMessage());            
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
