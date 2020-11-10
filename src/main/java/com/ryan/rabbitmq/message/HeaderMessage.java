package com.ryan.rabbitmq.message;

import java.io.Serializable;

public class HeaderMessage implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_04";

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String HEADER_KEY = "color";
    public static final String HEADER_VALUE = "red";

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
