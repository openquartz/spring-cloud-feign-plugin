package org.svnee.feign.plugin.starter.utils;

import java.util.Map;

/**
 * MapUtils
 *
 * @author svnee
 **/
public final class MapUtils {

    /**
     * is Empty map
     * @param map map
     * @return empty map
     */
    public static boolean isEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
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