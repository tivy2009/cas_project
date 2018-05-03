

/*
 * Copyright @ 2018 tivy
 * commonSecurity 2018-05-03 11:36:55
 * All right reserved.
 *
 */

package com.phy.common.security.utils;

import java.util.UUID;

/**
 * @desc: commonSecurity
 * @author: tivy
 * @createTime: 2018-05-03 11:36:55
 * @history:
 * @version: v1.0
 */

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
   }
    
}

