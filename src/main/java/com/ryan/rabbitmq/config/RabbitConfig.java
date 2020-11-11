package com.ryan.rabbitmq.config;

import com.ryan.rabbitmq.batchrabbitmq.message.DirectPushMessage;
import com.ryan.rabbitmq.message.DemoMessage;
import com.ryan.rabbitmq.message.HeaderMessage;
import com.ryan.rabbitmq.message.TopicMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class RabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo01Queue() {
            return new Queue(DemoMessage.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo01Exchange() {
            return new DirectExchange(DemoMessage.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：DemoMessage.EXCHANGE
        // Routing key：DemoMessage.ROUTING_KEY
        // Queue：DemoMessage.QUEUE
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(DemoMessage.ROUTING_KEY);
        }

    }


    /**
     * Topic Exchange 示例的配置类
     */
    public static class TopicExchangeDemoConfiguration {
        // 创建 Queue
        @Bean
        public Queue demo02Queue() {
            return new Queue(TopicMessage.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Topic Exchange
        @Bean
        public TopicExchange demo02Exchange() {
            return new TopicExchange(TopicMessage.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo02Message.EXCHANGE
        // Routing key：Demo02Message.ROUTING_KEY
        // Queue：Demo02Message.QUEUE
        @Bean
        public Binding demo02Binding() {
            return BindingBuilder.bind(demo02Queue()).to(demo02Exchange()).with(TopicMessage.ROUTING_KEY);
        }
    }


    /**
     * fanout Exchange 示例的配置类
     */
//    public static class FanoutExchangeDemoConfiguration {
//        // 创建 QueueA
//        @Bean
//        public Queue demo03QueueA() {
//            return new Queue(FanoutMessage.QUEUEA, // Queue 名字
//                    true, // durable: 是否持久化
//                    false, // exclusive: 是否排它
//                    false); // autoDelete: 是否自动删除
//        }
//
//        // 创建 QueueA
//        @Bean
//        public Queue demo03QueueB() {
//            return new Queue(FanoutMessage.QUEUEB, // Queue 名字
//                    true, // durable: 是否持久化
//                    false, // exclusive: 是否排它
//                    false); // autoDelete: 是否自动删除
//        }
//
//        // 创建 Topic Exchange
//        @Bean
//        public FanoutExchange demo03Exchange() {
//            return new FanoutExchange(FanoutMessage.EXCHANGE,
//                    true,  // durable: 是否持久化
//                    false);  // exclusive: 是否排它
//        }
//
//        // 创建 Binding
//        // Exchange：Demo02Message.EXCHANGE
//        // Routing key：Demo02Message.ROUTING_KEY
//        // Queue：Demo02Message.QUEUE
//        @Bean
//        public Binding demo03BindingA() {
//            return BindingBuilder.bind(demo03QueueA()).to(demo03Exchange());
//        }
//
//        @Bean
//        public Binding demo03BindingB() {
//            return BindingBuilder.bind(demo03QueueB()).to(demo03Exchange());
//        }
//    }


    /**
     * Header Exchange 示例配置
     */
    public static class HeadersExchangeDemoConfiguration {
        // 创建 QueueA
        @Bean
        public Queue demo04Queue() {
            return new Queue(HeaderMessage.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Topic Exchange
        @Bean
        public HeadersExchange demo04Exchange() {
            return new HeadersExchange(HeaderMessage.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo02Message.EXCHANGE
        // Routing key：Demo02Message.ROUTING_KEY
        // Queue：Demo02Message.QUEUE
        @Bean
        public Binding demo04BindingB() {
            return BindingBuilder.bind(demo04Queue()).to(demo04Exchange()).where(HeaderMessage.HEADER_KEY).matches(HeaderMessage.HEADER_VALUE);
        }
    }


    /**
     * 批量推送配置
     *
     * @param connectionFactory
     * @return
     */
    public static class batchPushConfiguration {

//        @Bean
//        public Queue batchPushQueue() {
//            return new Queue(DirectPushMessage.QUEUE, // Queue 名字
//                    true, // durable: 是否持久化
//                    false, // exclusive: 是否排它
//                    false); // autoDelete: 是否自动删除
//        }
//
//        // 创建 Direct Exchange
//        @Bean
//        public DirectExchange batchPushExchange() {
//            return new DirectExchange(DirectPushMessage.EXCHANGE,
//                    true,  // durable: 是否持久化
//                    false);  // exclusive: 是否排它
//        }
//
//        // 创建 Binding
//        // Exchange：DemoMessage.EXCHANGE
//        // Routing key：DemoMessage.ROUTING_KEY
//        // Queue：DemoMessage.QUEUE
//        @Bean
//        public Binding batchPushBinding() {
//            return BindingBuilder.bind(batchPushQueue()).to(batchPushExchange()).with(DirectPushMessage.ROUTING_KEY);
//        }

//        @Bean
//        public BatchingRabbitTemplate batchingRabbitTemplate(ConnectionFactory connectionFactory) {
//            int batchSize = 10;
//            int bufferLimit = 2048;
//            int timeout = 20000;
//            SimpleBatchingStrategy simpleBatchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);
//            ConcurrentTaskScheduler concurrentTaskScheduler = new ConcurrentTaskScheduler();
//            BatchingRabbitTemplate batchingRabbitTemplate = new BatchingRabbitTemplate(simpleBatchingStrategy, concurrentTaskScheduler);
//            batchingRabbitTemplate.setConnectionFactory(connectionFactory);
//            return batchingRabbitTemplate;
//        }

//        @Bean(name = "consumerBatchContainerFactory")
//        public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
//                SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//            // 创建 SimpleRabbitListenerContainerFactory 对象
//            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//            configurer.configure(factory, connectionFactory);
//            // <X> 额外添加批量消费的属性
//            factory.setBatchListener(true);
//            return factory;
//        }
    }



    /**
     * 消费者批量消费配置
     *
     * @param configurer
     * @param connectionFactory
     * @return
     */
    public static class batchConsumerConfiguration {

//        @Bean
//        public Queue batchPushQueue() {
//            return new Queue(DirectPushMessage.QUEUE, // Queue 名字
//                    true, // durable: 是否持久化
//                    false, // exclusive: 是否排它
//                    false); // autoDelete: 是否自动删除
//        }
//
//        // 创建 Direct Exchange
//        @Bean
//        public DirectExchange batchPushExchange() {
//            return new DirectExchange(DirectPushMessage.EXCHANGE,
//                    true,  // durable: 是否持久化
//                    false);  // exclusive: 是否排它
//        }
//
//        // 创建 Binding
//        // Exchange：DemoMessage.EXCHANGE
//        // Routing key：DemoMessage.ROUTING_KEY
//        // Queue：DemoMessage.QUEUE
//        @Bean
//        public Binding batchPushBinding() {
//            return BindingBuilder.bind(batchPushQueue()).to(batchPushExchange()).with(DirectPushMessage.ROUTING_KEY);
//        }
//
//        @Bean(name = "consumerBatchContainerFactory")
//        public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
//                SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//            // 创建 SimpleRabbitListenerContainerFactory 对象
//            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//            configurer.configure(factory, connectionFactory);
//            // <X> 额外添加批量消费的属性
//            factory.setBatchListener(true);
//
//            // 批量消费消息方式二配置
//            factory.setBatchSize(10);
//            factory.setReceiveTimeout(10 * 1000L);
//            factory.setConsumerBatchEnabled(true);
//            return factory;
//        }
    }
}
