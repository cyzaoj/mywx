package com.woawi.wx.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.controller
 * @date 15/11/21
 */
@Data
@ConfigurationProperties(prefix = "weixin", ignoreUnknownFields = false)
public class WxInfo {

    private String appId;

    private String secret;

    private String token;

    private String aesKey;
}