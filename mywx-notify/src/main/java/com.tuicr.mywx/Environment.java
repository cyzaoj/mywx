package com.tuicr.mywx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;

/**
 * @author ylxia
 * @version 1.0
 * @package com.woawi.wx
 * @date 15/11/23
 */
public abstract class Environment {

    @Getter
    @Setter
    private static MessageSource i18n = null;
}