package com.ryan.rabbitmq.batchrabbitmq.consumer;

import com.ryan.rabbitmq.batchrabbitmq.message.DirectPushMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = DirectPushMessage.QUEUE)
public class BatchDirectConsumer {

//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @RabbitHandler
//    public void onMessage(DirectPushMessage message) {
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//    }
}
