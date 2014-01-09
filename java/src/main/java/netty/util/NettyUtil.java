package netty.util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ServerChannel;

public class NettyUtil {

    public ServerBootstrap channel(
        ServerBootstrap bootstrap,
        Class<? extends ServerChannel> channelClazz) {

        return bootstrap.channel(channelClazz);

    }

}
