(ns netty.echo.server

  (:import
      (netty.util NettyUtil)
      (java.net InetSocketAddress)
      (io.netty.channel
        ChannelFuture
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
      (io.netty.channel.socket.nio NioServerSocketChannel)
    )

  (:gen-class
    :name netty.echo.server.EchoServerHandler
    :extends io.netty.channel.ChannelInboundHandlerAdapter
    :prefix "adapter-"
    :methods [
       [channelRead [io.netty.channel.ChannelHandlerContext Object] Void]
       [channelReadComplete [io.netty.channel.ChannelHandlerContext] Void]
       [exceptionCaught [io.netty.channel.ChannelHandlerContext java.lang.Throwable] Void]
    ]
  )

)

(defn adapter-channelRead [context data]
    "Reading the content of the bytes"
    ())

(defn adapter-channelReadComplete [context]
    "Once the message has been read then we flush the output"
    (let [once-flush
            (.writeAndFlush context Unpooled/EMPTY_BUFFER)]
            (.addListener once-flush ChannelFutureListener/CLOSE)))

(defn adapter-exceptionCaught [context throwable]
    "If anything goes wrong maybe we should handle it somehow"
    (.printStackTrace throwable)
    (.close context))

