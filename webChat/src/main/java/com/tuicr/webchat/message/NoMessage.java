package com.tuicr.webchat.message;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.message
 * @date 15/11/23
 */

public class NoMessage implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager)
            throws WxErrorException {
//        MessageSource source = Environment.getI18n();
//        String msg = source.getMessage("message.noregex", null, Locale.getDefault());
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage
                .TEXT()
                .content("message.noregex")
                .fromUser(wxMessage.getToUserName())
                .toUser(wxMessage.getFromUserName())
                .build();
        return m;
    }
}