package com.tuicr.webchat.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.controller
 * @date 15/11/21
 */
@Slf4j
@RestController
@RequestMapping("wx")
public class WxStartController implements InitializingBean {


    @Autowired
    protected WxMpService wxMpService;

    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    protected WxMpMessageRouter wxMpMessageRouter;


    public static final String[] _ENC_TYPE = {"raw", "aes"};

    /**
     * 微信入口点
     *
     * @param signature    验证
     * @param nonce        说明是一个仅仅用来验证的请求
     * @param timestamp    时间
     * @param echostr
     * @param encType
     * @param msgSignature
     * @param request
     * @return
     */
    @RequestMapping(value = "main", method = {RequestMethod.POST, RequestMethod.GET})
    public String startWx(
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "timestamp", required = false) String timestamp,
            @RequestParam(value = "echostr", required = false) String echostr,
            @RequestParam(value = "encrypt_type", required = false) String encType,
            @RequestParam(value = "msg_signature", required = false) String msgSignature,
            HttpServletRequest request
    ) {
        log.info("[weixin start] --> [signature={},timestamp={},echostr={},nonce={},encrypt_type={},msg_signature={}]"
                , signature
                , timestamp
                , echostr
                , nonce
                , encType
                , msgSignature);


        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            log.debug("Check echostr = {} success.", echostr);
            return echostr;
        }

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            log.error("Check Signature failure !!!");
            return "sorry ,I'm tired.";
        }

        encType = StringUtils.isBlank(encType) ?
                _ENC_TYPE[0] :
                encType;

        try {
            if (_ENC_TYPE[0].equals(encType)) {
                // 明文传输的消息
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                String result = null == outMessage ? StringUtils.EMPTY : outMessage.toXml();
                log.debug("callback result={}", result);
                return result;
            } else if (_ENC_TYPE[1].equals(encType)) {
                // 加密传输消息
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
                        request.getInputStream(),
                        wxMpConfigStorage,
                        timestamp,
                        nonce,
                        msgSignature
                );
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                String result = null == outMessage ? StringUtils.EMPTY : outMessage.toEncryptedXml(wxMpConfigStorage);
                log.debug("callback result={}", result);
                return result;

            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            log.info("[weixin end]");
        }
        return "what language you speak?";
    }

    @Override
    public void afterPropertiesSet() throws Exception {


    }


}