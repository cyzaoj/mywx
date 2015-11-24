package com.woawi.wx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx.controller
 * @date 15/11/22
 */

@Slf4j
@RestController
@RequestMapping("main")
public class MainController {


    /**
     * 返回时间
     *
     * @return
     */
    @RequestMapping("test")
    public String test() {
        Date nowDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return sdf.format(nowDate);
    }
}