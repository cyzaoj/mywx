package com.tuicr.webchat.server;

import lombok.Data;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx
 * @date 15/11/26
 */
@Data
public class DataModelResult<T> {


    /**
     * 状态码
     */
    private long stateCode;


    /**
     * 数据结果
     */
    private T result;

    /**
     * 返回状态消息内容
     * 错误消息
     */
    private String message;

    /**
     * 时间
     */
    private long timestamp = System.currentTimeMillis();
}