(ns netty.echo

    (:gen-class
     :name netty.echo.EchoServer
     :prefix "-")

    (:import
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
      (.group bootstrap group)

    )
  )

)

