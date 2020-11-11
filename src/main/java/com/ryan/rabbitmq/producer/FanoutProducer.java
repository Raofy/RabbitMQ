package com.ryan.rabbitmq.producer;

import com.ryan.rabbitmq.message.FanoutMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo03Message 消息
        FanoutMessage message = new FanoutMessage();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(FanoutMessage.EXCHANGE, null, message);
    }
}
