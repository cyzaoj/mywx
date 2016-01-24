package com.tuicr.webchat.controller;

import com.tuicr.webchat.server.DataModelResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.controller
 * @date 15/11/26
 */
@Slf4j
@RestController
@RequestMapping("customMenu")
public class CustomMenuController {


    @Autowired
    private WxMpService wxMpService;


    /**
     * 创建微信菜单
     *
     * @return
     */
    @RequestMapping(
            value = "create",
            method = {RequestMethod.POST, RequestMethod.GET}
    )
    public DataModelResult<List<WxMenu.WxMenuButton>> createMenu() {
        DataModelResult<List<WxMenu.WxMenuButton>> result = new DataModelResult<>();
        WxMenu wxMenu = new WxMenu();
        try {
            WxMenu.WxMenuButton button = new WxMenu.WxMenuButton();
            button.setName("test");
            button.setUrl("http://www.baidu.com");
            button.setType("view");
            wxMenu.getButtons().add(button);
            wxMpService.menuCreate(wxMenu);

        } catch (WxErrorException e) {
            WxError wxError = e.getError();
            log.error("create CustomMenu failure e={}", wxError.toString());
            result.setMessage(wxError.getErrorMsg());
            result.setStateCode(wxError.getErrorCode());
        }
        return result;
    }


    public DataModelResult<Boolean> removeMenu() {
        return null;
    }
}