package com.reach.common.utils;

import com.github.yitter.idgen.YitIdHelper;

public class IdUtil {
    /**
     * generate unique id
     * @return id
     */
    public static String getId() {
        return String.valueOf(YitIdHelper.nextId());
    }
}
