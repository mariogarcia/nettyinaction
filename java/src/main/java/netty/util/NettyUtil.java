package netty.util;

import java.net.InetSocketAddress;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ServerChannel;

public class NettyUtil {

    public ServerBootstrap channel(ServerBootstrap bootstrap, Class<? extends ServerChannel> channelClazz) {
        return bootstrap.channel(channelClazz);
    }

    public ServerBootstrap group(ServerBootstrap bootstrap, NioEventLoopGroup group) {
        return bootstrap.group(group);
    }

    public ServerBootstrap localAddress(ServerBootstrap bootstrap, InetSocketAddress address) {
        return bootstrap.localAddress(address);
    }

}
