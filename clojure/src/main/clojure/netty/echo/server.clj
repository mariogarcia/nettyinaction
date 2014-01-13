(ns netty.echo.server

    (:gen-class
     :name netty.echo.server.EchoServerHandler
     :extends io.netty.channel.ChannelInboundHandlerAdapter
     :prefix "_adapter_"
     :methods [
       [channelRead [io.netty.channel.ChannelHandlerContext Object] Void]
       [channelReadComplete [io.netty.channel.ChannelHandlerContext] Void]
       [exceptionCaught [io.netty.channel.ChannelHandlerContext Throwable] Void]
     ]
    )

  (:import
      (netty.util NettyUtil)
      (io.netty.bootstrap ServerBootstrap)
      (io.netty.channel ChannelFuture EventLoopGroup ChannelInitializer)
      (io.netty.channel.nio NioEventLoopGroup)
      (io.netty.channel.socket SocketChannel)
      (io.netty.channel.ChannelInboundHandlerAdapter)
      (io.netty.channel.ChannelHandlerContext)
      (io.netty.channel.socket.nio NioServerSocketChannel)
      (java.net InetSocketAddress)
    )


)

(defn _adapter_channelRead [context data] (

))

(defn _adapter_channelReadComplete [context] (

))

(defn _adapter_exceptionCaught [context throwable] (

))
