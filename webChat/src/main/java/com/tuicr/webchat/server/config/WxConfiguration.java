package com.tuicr.webchat.server.config;

import com.tuicr.webchat.message.FocusMeMessage;
import com.tuicr.webchat.server.properties.WxOauth;
import com.tuicr.webchat.util.WxMpUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.server.config
 * @date 15/11/21
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WxOauth.class)
public class WxConfiguration {

    @Bean
    public WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage(WxOauth info) {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(info.getAppId()); // 设置微信公众号的appid
        config.setSecret(info.getSecret()); // 设置微信公众号的app corpSecret
        config.setToken(info.getToken()); // 设置微信公众号的token
        config.setAesKey(info.getAesKey()); // 设置微信公众号的EncodingAESKey
        return config;
    }


    @Bean
    public WxMpService wxMpService(WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage) {
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        return wxService;
    }


    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);

        WxMpUtil.text(wxMpMessageRouter, false, "test", "This is a test !!!");
        wxMpMessageRouter.rule()
                // .msgType(WxConsts.XML_MSG_TEXT)
                .async(false)
                .event(WxConsts.EVT_UNSUBSCRIBE)
                // .eventKey("EVENT_KEY")
                .handler(new FocusMeMessage())
                .end()
                .rule()
                .async(false)
                .event(WxConsts.EVT_SUBSCRIBE)
                .handler(new FocusMeMessage())
                .end();
        // wxMpMessageRouter.rule().handler(new NoMessage()).end();
        return wxMpMessageRouter;
    }
}