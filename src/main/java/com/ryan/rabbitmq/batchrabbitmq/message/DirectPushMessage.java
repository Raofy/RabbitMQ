package com.ryan.rabbitmq.batchrabbitmq.message;

import java.io.Serializable;

public class DirectPushMessage implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_batch";

    public static final String EXCHANGE = "EXCHANGE_DEMO_batch";

    public static final String ROUTING_KEY = "ROUTING_KEY_batch";

    /**
     * 编号
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
