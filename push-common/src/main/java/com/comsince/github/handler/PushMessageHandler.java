package com.comsince.github.handler;

import com.comsince.github.PushPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;
import java.nio.ByteBuffer;

/**
 *
 * 处理推送消息的处理器
 * */
public class PushMessageHandler implements ServerAioHandler,ClientAioHandler {
    Logger logger = LoggerFactory.getLogger(PushMessageHandler.class);

    @Override
    public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        PushPacket pushPacket = new PushPacket();
        return pushPacket.decode(buffer,readableLength,channelContext);
    }

    /**
     *
     * 编码数据，对要发送的数据，进行响应的协议转换
     * */
    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        PushPacket pushPacket = (PushPacket) packet;
        return pushPacket.encode();
    }

    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {

    }

    @Override
    public Packet heartbeatPacket(ChannelContext channelContext) {
        return null;
    }
}
