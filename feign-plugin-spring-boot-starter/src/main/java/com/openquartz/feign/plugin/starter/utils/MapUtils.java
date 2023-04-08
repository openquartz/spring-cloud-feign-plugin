package com.openquartz.feign.plugin.starter.utils;

import java.util.Map;

/**
 * MapUtils
 *
 * @author svnee
 **/
public final class MapUtils {

    private MapUtils() {
    }

    /**
     * is Empty map
     * @param map map
     * @return empty map
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * is not empty
     * @param map map
     * @return not empty
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

}
