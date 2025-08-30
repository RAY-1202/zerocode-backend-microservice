package com.ray.zerocode.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;

/**
 * 缓存 key 生成工具类
 *
 */
public class CacheKeyUtils {

    /**
     * 根据对象生成缓存key (JSON + MD5)
     *
     * @param obj 要生成key的对象
     * @return MD5哈希后的缓存key
     */
    public static String generateKey(Object obj) {
        if (obj == null) {
            return DigestUtil.md5Hex("null");
        }
        // 先转JSON，再MD5
        //JSON 序列化：确保对象内容的一致性，相同内容的对象生成相同的字符串
        //MD5 哈希：将长字符串转换为固定长度的字符串，避免 Redis key 过长
        String jsonStr = JSONUtil.toJsonStr(obj);
        return DigestUtil.md5Hex(jsonStr);
    }
}
