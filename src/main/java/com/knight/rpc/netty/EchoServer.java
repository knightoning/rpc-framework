package com.knight.rpc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new EchoServer().bind(port);

    }

    public void bind(int port) throws Exception{

        //创建两个EventLoopGroup实例
        //EventLoopGroup 是包含一组专门用于处理网络事件的NIO线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务端辅助启动类ServerBootstrap对象

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)

                    //设置 NioServerSocketChannel,对应于JDK NIO 类ServerSocketChannel
                    .channel(NioServerSocketChannel.class)

                    //设置TCP参数，连接请求的最大队列长度
                    .option(ChannelOption.SO_BACKLOG, 1024)

                    //设置I/O事件处理类，用来处理消息的编解码及我们的业务逻辑
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                            nioSocketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {

            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
