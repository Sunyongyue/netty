package com.tianhe.netty_handle.consumer;



import com.tianhe.netty_handle.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_B)
public class MyConsumer2 {



    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(List<String> instructionIds) {
        logger.info("接收处理队列B当中的消息： " + instructionIds);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
