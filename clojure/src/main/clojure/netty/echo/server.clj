(ns netty.echo.server
  (:import (netty.util NettyUtil)
           (java.net InetSocketAddress)
           (io.netty.channel ChannelFuture
                             ChannelFutureListener
                             ChannelInitializer
                             ChannelHandlerContext
                             ChannelInboundHandlerAdapter
                             EventLoopGroup)
           (io.netty.util CharsetUtil)
           (io.netty.bootstrap ServerBootstrap)
           (io.netty.buffer Unpooled)
           (io.netty.channel.nio NioEventLoopGroup)
           (io.netty.channel.socket SocketChannel)
           (io.netty.channel.socket.nio NioServerSocketChannel)))

(defn channel-adapter
  []
  (proxy [ChannelInboundHandlerAdapter] []
    (channelRead [context data]
      "What to do every time something arrives through the channel"
      (let [bytesToRead   (.readableBytes data)
            messageBytes  (.readBytes bytesToRead)
            message       (.toString messageBytes CharsetUtil/UTF_8)]

        (.writeAndFlush message)))

    (channelReadComplete [context]
      "Once the channel reading has been completed"
      (let [once-flush (.writeAndFlush context Unpooled/EMPTY_BUFFER)]
        (.addListener once-flush ChannelFutureListener/CLOSE)))

    (exceptionCaught [context throwable]
      "If something goes wrong"
      (.printStackTrace throwable)
      (.close context))))

(defn channel-initializer
    []
    (proxy [ChannelInitializer] []
      (initChannel [channel]
        "We are initializing the channel with a specific channel handler"
        (.addLast (.pipeline channel) channel-adapter))))


