package com.ryan.rabbitmq.consumer;

import com.ryan.rabbitmq.message.DemoMessage;
import com.ryan.rabbitmq.message.TopicMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = TopicMessage.QUEUE)
public class Topic {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(TopicMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
