package com.huayan.Server;

import com.huayan.DAO.MarshallingCodeCFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class Server {

    public static void main(String[] args) throws Exception {
        // 1 创建线两个事件循环组
        // 一个是用于处理服务器端接收客户端连接的
        // 一个是进行网络通信的（网络读写的）
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();
        
       
        // 2 创建辅助工具类ServerBootstrap，用于服务器通道的一系列配置
        ServerBootstrap b = new ServerBootstrap();
        
        b.group(pGroup, cGroup) // 绑定俩个线程组
                .channel(NioServerSocketChannel.class) // 指定NIO的模式.NioServerSocketChannel对应TCP, NioDatagramChannel对应UDP
                .option(ChannelOption.SO_BACKLOG, 1024) // 设置TCP缓冲区
                //设置日志
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                	
                   protected void initChannel(SocketChannel sc) throws Exception {
                       sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                       sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                       sc.pipeline().addLast(new ServerHeartBeatHandler());
                    
                   }
               });
               
               ChannelFuture cf = b.bind(8765).sync();
               cf.channel().closeFuture().sync();
               pGroup.shutdownGracefully();
               cGroup.shutdownGracefully();
           }
}
