package com.comsince.github;

import com.comsince.github.handler.PushConnectorListener;
import com.comsince.github.handler.PushMessageHandler;
import io.netty.util.internal.StringUtil;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tio.cluster.TioClusterConfig;
import org.tio.cluster.kafka.KafkaTioClusterTopic;
import org.tio.cluster.redisson.RedissonTioClusterTopic;
import org.tio.server.ServerGroupContext;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;
import org.redisson.config.Config;

/**
 * @author comsicne
 *         Copyright (c) [2019] [Meizu.inc]
 * @Time 19-2-14 上午10:21
 **/
public class PushServer {

    private Logger logger = LoggerFactory.getLogger(PushServer.class);

    //handler, 包括编码、解码、消息处理
    public static ServerAioHandler aioHandler = new PushMessageHandler();

    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ServerAioListener aioListener = new PushConnectorListener();

    //一组连接共用的上下文对象
    public static ServerGroupContext serverGroupContext = new ServerGroupContext("push-conector-tio-server", aioHandler, aioListener);

    //tioServer对象
    public static TioServer tioServer = new TioServer(serverGroupContext);

    //有时候需要绑定ip，不需要则null
    public static String serverIp = null;

    //监听的端口
    public static int serverPort = Const.PORT;

    //集群配置
    public static TioClusterConfig tioClusterConfig;


//    public void init(RedissonClient redissonClient) throws IOException{
//        //tioClusterConfig = new TioClusterConfig(new RedissonTioClusterTopic("push-channel",redissonClient));
//        tioClusterConfig = new TioClusterConfig(new KafkaTioClusterTopic("push-channel",""));
//        serverGroupContext.setTioClusterConfig(tioClusterConfig);
//        serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
//        tioServer.start(serverIp, serverPort);
//    }

    public void init(String broker) throws IOException{
        if(!StringUtil.isNullOrEmpty(broker)){
            logger.info("start push-connector cluster current kafka broker is "+broker);
            tioClusterConfig = new TioClusterConfig(new KafkaTioClusterTopic("push-channel",broker));
            serverGroupContext.setTioClusterConfig(tioClusterConfig);
        }
        serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
        tioServer.start(serverIp, serverPort);
    }

}
