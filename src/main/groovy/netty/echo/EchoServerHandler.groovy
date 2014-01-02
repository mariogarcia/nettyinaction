package netty.echo

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
/**
 *
 */
class EchoServerHandler extends ChannelInboundHandlerAdapter {

        void channelRead(ChannelHandlerContext context, Object message) {
            println "Server received : $message"
            context.write(message)
        }

        void channelReadComplete(ChannelHandlerContext context) {
            context.writeAndFlush(Unpooled.EMPTY_BUFFER).
                addListener(ChannelFutureListener.CLOSE)
        }

        void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
            cause.printStacktrace()
            context.close()
        }

}


