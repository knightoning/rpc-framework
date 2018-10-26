package com.knight.rpc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;

public class EchoServerHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {

        //接受客户端发来的数据，使用buf.readableBytes()获取数据大小，并转换成byte数组
        ByteBuf buf = (ByteBuf)msg;

        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        //将byte数组转成字符串，在控制台打印输出
        String body = new String(req,"UTF-8");
        System.out.println("receive data from client:" + body);

        //将接收到客户端发来的数据回写到客户端
        ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
        channelHandlerContext.write(resp);

    }

    //发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable cause) throws Exception{
        channelHandlerContext.close();
    }

    //将发送缓冲区的消息全部写入SocketChannel中
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception{
        channelHandlerContext.flush();
    }

}
