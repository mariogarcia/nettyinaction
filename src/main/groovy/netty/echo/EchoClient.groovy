package netty.echo

import java.net.InetSocketAddress

import io.netty.bootstrap.Bootstrap

import io.netty.channel.EventLoopGroup
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer

import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
/**
 * EchoClient.groovy
 *
 */
class EchoClient {

    String host
    int port

    void start() {

        EventLoopGroup group = new NioEventLoopGroup()

        try {

            Bootstrap bootstrap = new Bootstrap()

            bootstrap.
                group(group).
                channel(NioSocketChannel).
                remoteAddress(new InetSocketAddress(host, port)).
                handler(new ChannelInitializer<SocketChannel>() {
                    void initChannel(SocketChannel channel) {
                        channel.pipeline().
                            addLast(new EchoClientHandler())
                    }
                })

            ChannelFuture future = bootstrap.connect().sync()
            future.channel().closeFuture().sync()

        } finally {
            println "Ahhhhh"
            group.shutdownGracefully().sync()
        }

    }

    static void main(args) {
        new EchoClient(host: '127.0.0.1', port: 8080).start()
    }


}


