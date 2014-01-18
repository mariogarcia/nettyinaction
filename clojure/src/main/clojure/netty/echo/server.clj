(ns netty.echo.server
  (:import (netty.util NettyUtil)
           (java.net InetSocketAddress)
           (io.netty.channel ChannelFuture
                             ChannelFutureListener
                             ChannelInitializer
                             ChannelHandlerContext
                             ChannelInboundHandlerAdapter
                             EventLoopGroup)
           (io.netty.bootstrap ServerBootstrap)
           (io.netty.buffer Unpooled)
           (io.netty.util CharsetUtil)
           (io.netty.channel.nio NioEventLoopGroup)
           (io.netty.channel.socket SocketChannel)
           (io.netty.channel.socket.nio NioServerSocketChannel)))

(defn channel-adapter
  []
  (proxy [ChannelInboundHandlerAdapter] []
    (channelRead [context data]
      (let [readableBytes []]

        ))

    (channelReadComplete [context]
      (let [once-flush (.writeAndFlush context Unpooled/EMPTY_BUFFER)]
        (.addListener once-flush ChannelFutureListener/CLOSE)))

    (exceptionCaught [context throwable]
      (.printStackTrace throwable)
      (.close context))))
