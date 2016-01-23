package com.tuicr.webchat;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目启动完成初始化
 *
 * @author ylxia
 * @version 1.0
 * @package com.iyerka
 * @date 2015-08-21 15:51:19
 */
@Slf4j
@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();


        // WxMpMessageRouter router = context.getBean(WxMpMessageRouter.class);


    }


}