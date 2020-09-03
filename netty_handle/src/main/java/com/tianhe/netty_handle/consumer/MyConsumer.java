package com.tianhe.netty_handle.consumer;

import com.alibaba.fastjson.JSONObject;

import com.jnthyb.protocol.decode.ProtocolDecodeMain;
import com.tianhe.netty_handle.config.RabbitConfig;
import com.tianhe.netty_handle.entity.CustInfo;
import com.tianhe.netty_handle.service.ICustInfoService;
import com.tianhe.netty_handle.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MyConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String str) {
        logger.info("接收处理队列A当中的消息： " + str);
        String decode = ProtocolDecodeMain.decode(str);
        JSONObject object = JSONObject.parseObject(decode);
        System.out.println(object);
        redisUtils.lSet("res",str);
        List<Object> res = redisUtils.lGet("res", 0, 0);
//      redisUtils.lRemove()
        List<CustInfo> list = service.list();
        System.out.println(list);
        System.out.println(res.size());
    }

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ICustInfoService service;
    public void decodeData(JSONObject jsonObject) throws Exception {



    }

}
