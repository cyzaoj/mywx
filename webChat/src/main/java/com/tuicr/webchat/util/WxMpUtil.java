package com.tuicr.webchat.util;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.util
 * @date 15/11/22
 */
public final class WxMpUtil {


    /**
     * @param wxMpMessageRouter 微信路由
     * @param async
     * @param ask
     * @param answer
     */
    public static void text(
            WxMpMessageRouter wxMpMessageRouter,
            boolean async,
            String ask,
            String answer
    ) {
        WxMpMessageHandler handler = (wxMessage, context, wxMpService, sessionManager) -> {
            WxMpXmlOutTextMessage m
                    = WxMpXmlOutMessage
                    .TEXT()
                    .content(answer)
                    .fromUser(wxMessage.getToUserName())
                    .toUser(wxMessage.getFromUserName())
                    .build();
            return m;
        };
        wxMpMessageRouter
                .rule()
                .async(async)
                .content(ask)
                .handler(handler)
                .end();
    }
}