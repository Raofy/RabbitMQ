package com.ryan.rabbitmq.consumer;

import com.ryan.rabbitmq.message.DemoMessage;
import com.ryan.rabbitmq.message.FanoutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Fanout {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = FanoutMessage.QUEUEA)
    @RabbitHandler
    public void onMessageA(FanoutMessage message) {
        logger.info("[onMessageA][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

    @RabbitListener(queues = FanoutMessage.QUEUEB)
    @RabbitHandler
    public void onMessageB(FanoutMessage message) {
        logger.info("[onMessageB][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
