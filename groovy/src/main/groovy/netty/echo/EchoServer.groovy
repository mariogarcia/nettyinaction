package netty.echo

import java.net.InetSocketAddress

import io.netty.bootstrap.ServerBootstrap

import io.netty.channel.ChannelFuture
import io.netty.channel.EventLoopGroup
import io.netty.channel.ChannelInitializer

import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

/**
 *
 */
class EchoServer {

    int port

    void start() {

        EventLoopGroup group = new NioEventLoopGroup()

        try {

            ServerBootstrap bootstrap = new ServerBootstrap()

            bootstrap.
                group(group).
                channel(NioServerSocketChannel).
                localAddress(new InetSocketAddress(port)).
                childHandler(new ChannelInitializer<SocketChannel>() {
                    void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new EchoServerHandler())
                    }
                })

            ChannelFuture future = bootstrap.bind().sync()
            future.channel().closeFuture().sync()

        } finally {
            group.shutdownGracefully().sync()
        }

    }

    static void main(args) {
        new EchoServer(port: 8080).start()
    }

}


