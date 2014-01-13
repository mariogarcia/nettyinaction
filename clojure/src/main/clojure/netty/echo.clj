(ns netty.echo

    (:gen-class
     :name netty.echo.EchoServer
     :prefix "-")

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

