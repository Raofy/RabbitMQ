package com.ryan.rabbitmq;

import com.ryan.batchconsumer.producer.DirectBatchConsumerProducer;
import com.ryan.batchrabbitmq.producer.DirectBatchPushProducer;
import com.ryan.rabbitmq.message.HeaderMessage;
import com.ryan.rabbitmq.task.DirectProducer;
import com.ryan.rabbitmq.task.FanoutProducer;
import com.ryan.rabbitmq.task.HeadersProducer;
import com.ryan.rabbitmq.task.TopicProducer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class RabbitmqApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DirectProducer directProducer;

    @Test
    void contextLoads() {
    }

    /**
     * 测试同步发送
     *
     * @throws InterruptedException
     */
    @Test
    void testDirectSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        directProducer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void tesDirectSyncSendDefault() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        directProducer.syncSendDefault(id);
        logger.info("[tesSyncSendDefault][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testDirectAsyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        directProducer.asyncSend(id).addCallback(new ListenableFutureCallback<Void>() {

            @Override
            public void onFailure(Throwable e) {
                logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

            @Override
            public void onSuccess(Void aVoid) {
                logger.info("[testASyncSend][发送编号：[{}] 发送成功，发送成功]", id);
            }

        });
        logger.info("[testASyncSend][发送编号：[{}] 调用完成]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    /**
     * Topic路由测试
     */

    @Autowired
    private TopicProducer producer;

    @Test
    public void testTopicSyncSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "da.yu.nai");
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testTopicSyncSendFailure() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "yu.nai.shuai");
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    /**
     * fanout路由测试
     */

    @Autowired
    private FanoutProducer fanoutProducer;

    @Test
    public void testFanoutSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        fanoutProducer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    /**
     * Headers路由测试
     */
    @Autowired
    private HeadersProducer headersProducer;

    @Test
    public void testHeadersSyncSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        headersProducer.syncSend(id, HeaderMessage.HEADER_VALUE);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testHeadersSyncSendFailure() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        headersProducer.syncSend(id, "error");
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }





    /**
     * 批量推送消息
     *
     */
    @Autowired
    private DirectBatchPushProducer batchRabbitMqProducer;

    @Test
    public void testBatchRabbitSyncSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            batchRabbitMqProducer.syncSend(id);

            // 故意每条消息之间，隔离 10 秒
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }












    /**
     * 消费者批量消费
     */
    @Autowired
    private DirectBatchConsumerProducer batchConsumerProducer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            batchConsumerProducer.syncSend(id);

            // 故意每条消息之间，隔离 10 秒
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
