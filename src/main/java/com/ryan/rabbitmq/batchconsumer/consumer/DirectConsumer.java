package com.ryan.rabbitmq.batchconsumer.consumer;

import com.ryan.rabbitmq.batchrabbitmq.message.DirectPushMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@RabbitListener(queues = DirectPushMessage.QUEUE, containerFactory = "consumerBatchContainerFactory")
public class DirectConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @RabbitHandler
    public void onMessage(List<DirectPushMessage> message) {
//        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        logger.info("[onMessage][线程编号:{} 消息数：{}]", Thread.currentThread().getId(), message.size());
    }
}
