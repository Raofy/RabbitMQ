package com.ryan.rabbitmq.producer;

import com.ryan.rabbitmq.message.TopicMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String routingKey) {
        // 创建 Demo02Message 消息
        TopicMessage message = new TopicMessage();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(TopicMessage.EXCHANGE, routingKey, message);
    }
}
