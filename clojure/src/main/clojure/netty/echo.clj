(ns netty.echo

    (:gen-class
     :name netty.echo.EchoServer
     :prefix "-")

    (:gen-class
     :name netty.echo.EchoServerHandler
     :extends ChannelInboundHandlerAdapter
     :prefix "_adapter_"
     :methods [
       [channelRead [ChannelHandlerContext Object] Void]
       [channelReadComplete [ChannelHandlerContext] Void]
       [exceptionCaught [ChannelHandlerContext Throwable] Void]
     ]
    )

    (:import
      (netty.util NettyUtil)
      (io.netty.bootstrap ServerBootstrap)
      (io.netty.channel ChannelFuture EventLoopGroup ChannelInitializer)
      (io.netty.channel.nio NioEventLoopGroup)
      (io.netty.channel.socket SocketChannel)
      (io.netty.channel.socket.nio NioServerSocketChannel)
      (java.net InetSocketAddress)
    )

)

(defn -main []
  "This example builds an echo server with Netty"
  (let [group (NioEventLoopGroup.)]
    (let [bootstrap (ServerBootstrap.)]

      (doto (NettyUtil.)
        (.group bootstrap group)
        (.channel bootstrap NioServerSocketChannel)
        (.localAddress bootstrap (InetSocketAddress. 8080))
      )

      (let [fut (.sync (.bind bootstrap))]
        (.sync (.closeFuture (.channel fut))))

    )
  )
)

(defn _adapter_channelRead [context data] (

))

(defn _adapter_channelReadComplete [context] (

))

(defn _adapter_exceptionCaught [context throwable] (

))
