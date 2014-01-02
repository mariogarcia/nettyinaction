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

    final int port

    EchoServer(int port) {
        this.port = port
    }

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
            println("${EchoServer.simpleName} listening on ${future.channel().localAddress()}")
            future.channel().closeFuture().sync()

        } finally {
            group.shutdownGracefully().sync()
        }

    }

    static void main(args) {
        new EchoServer(8080).start()
    }

}


