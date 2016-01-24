package com.tuicr.webchat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.controller
 * @date 15/11/24
 */

@Slf4j
@Controller
@RequestMapping("index")
public class IndexController {

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String index() {
        Date nowDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return sdf.format(nowDate);
    }
}