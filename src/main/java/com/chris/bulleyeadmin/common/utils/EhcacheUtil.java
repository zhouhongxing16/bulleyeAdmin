package com.chris.bulleyeadmin.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by Chris on 2017-08-16.
 */
public class EhcacheUtil {
    public static CacheManager manager = CacheManager.create();

    public static String cacheName = "metaCache";

    public static Object get(Object key) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return element.getObjectValue();
            }
        }
        return null;
    }

    public static void put(Object key, Object value) {
        Cache cache = manager.getCache(cacheName);
        if (cache != null)
            cache.put(new Element(key, value));
    }

    public static boolean remove(Object key)
    {
        Cache cache = manager.getCache(cacheName);
        if (cache != null) {
            return cache.remove(key);
        }
        return false;
    }

}
