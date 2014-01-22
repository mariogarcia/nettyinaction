(ns netty.echo
  (:import (netty.util NettyUtil)
           (io.netty.bootstrap ServerBootstrap)
           (io.netty.channel ChannelFuture EventLoopGroup ChannelInitializer)
           (io.netty.channel.nio NioEventLoopGroup)
           (io.netty.channel.socket SocketChannel)
           (io.netty.channel.socket.nio NioServerSocketChannel)
           (java.net InetSocketAddress))
  (:require [netty.echo.server :refer [channel-adapter channel-initializer]])
  (:gen-class))

(defn -main
  []
  "This example builds an echo server with Netty"
  (let [group       (NioEventLoopGroup.)
        bootstrap   (ServerBootstrap.)
        initializer (channel-initializer)]

    (doto (NettyUtil.)
      (.group bootstrap group)
      (.channel bootstrap NioServerSocketChannel)
      (.localAddress bootstrap (InetSocketAddress. 8080)))
      (.childHandler bootstrap initializer)

    (let [fut (.sync (.bind bootstrap))]
      (.sync (.closeFuture (.channel fut))))))



