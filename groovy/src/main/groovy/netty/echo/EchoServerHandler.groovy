package netty.echo

import groovy.util.logging.Log

import io.netty.util.CharsetUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 *
 */
@Log
class EchoServerHandler extends ChannelInboundHandlerAdapter {

        void channelRead(ChannelHandlerContext context, Object data) {
            def message = data.readBytes(data.readableBytes()).toString(CharsetUtil.UTF_8)
                log.info "Server received : $message"
            context.writeAndFlush(message)
        }

        void channelReadComplete(ChannelHandlerContext context) {
            context.
                writeAndFlush(Unpooled.EMPTY_BUFFER).
                addListener(ChannelFutureListener.CLOSE)
        }

        void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
            cause.printStacktrace()
            context.close()
        }

}


