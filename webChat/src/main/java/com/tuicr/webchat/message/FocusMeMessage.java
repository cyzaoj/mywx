package com.tuicr.webchat.message;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Map;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.message
 * @date 15/11/25
 */
@Slf4j
public class FocusMeMessage implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
       // String msg = source.getMessage("message.welcome", null, Locale.getDefault());
        String event = StringUtils.isBlank(wxMessage.getEvent()) ? StringUtils.EMPTY : wxMessage.getEvent();
        WxMpXmlOutTextMessage m = null;
        if (WxConsts.EVT_SUBSCRIBE.equals(event)) {
            m
                    = WxMpXmlOutMessage
                    .TEXT()
                    .content("message.welcome")
                    .fromUser(wxMessage.getToUserName())
                    .toUser(wxMessage.getFromUserName())
                    .build();
        } else if (WxConsts.EVT_UNSUBSCRIBE.equals(event)) {
        }
        log.info("{} ---> {}", event, ToStringBuilder.reflectionToString(wxMessage));
        return m;
    }
}