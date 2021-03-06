package netty.echo

import groovy.util.logging.Log

import io.netty.util.CharsetUtil
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * EchoClientHandler.groovy
 *
 */
@Log
@Sharable
class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    void channelActive(ChannelHandlerContext context) {
        context.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8))
    }

    void channelRead0(ChannelHandlerContext context, ByteBuf data) {
        def message = ByteBufUtil.hexDump(data.readBytes(data.readableBytes()))
        log.info "Client received: $message"
    }

    void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStacktrace()
        context.close()
    }

}


