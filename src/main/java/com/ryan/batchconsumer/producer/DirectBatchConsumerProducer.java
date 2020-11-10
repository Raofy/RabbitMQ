package com.ryan.batchconsumer.producer;

import com.ryan.batchconsumer.message.DirectMessage;
import com.ryan.rabbitmq.message.DemoMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class DirectBatchConsumerProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 同步发送消息
     *
     * @param id
     */
    public void syncSend(Integer id) {
        DirectMessage demoMessage = new DirectMessage();
        demoMessage.setId(id);
        rabbitTemplate.convertAndSend(DirectMessage.EXCHANGE, DirectMessage.ROUTING_KEY, demoMessage);
    }

    public void syncSendDefault(Integer id) {
        DirectMessage demoMessage = new DirectMessage();
        demoMessage.setId(id);
        rabbitTemplate.convertAndSend(DirectMessage.QUEUE, demoMessage);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            // 发送消息
            this.syncSend(id);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }
}
