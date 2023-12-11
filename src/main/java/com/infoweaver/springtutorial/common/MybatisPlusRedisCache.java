package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Ruobing Shang 2023-10-12 17:47
 */
@Slf4j
public class MybatisPlusRedisCache implements Cache {
    /**
     * redis连接template
     */
    private static RedisTemplate<String, Object> redisTemplate;
    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    /**
     * 缓存id
     */
    private final String id;
    /**
     * Mybatis Plus缓存过期时间1分钟
     */
    private static final long EXPIRE_TIME_IN_MINUTES = 1;

    public MybatisPlusRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    /**
     * @return RedisTemplate
     * @issue ERROR! The context of this bean cannot be updated when the context is updated!!!
     */
    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> getRedisTemplate() {
        return (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate = getRedisTemplate();
        if (value != null) {
            redisTemplate.opsForHash().put(id, key.toString(), value);
            redisTemplate.expire(id, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        }
    }

    @Override
    public Object getObject(Object key) {
        redisTemplate = getRedisTemplate();
        try {
            if (key != null) {
                return redisTemplate.opsForHash().get(id, key.toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        redisTemplate = getRedisTemplate();
        if (key != null) {
            redisTemplate.delete(key.toString());
        }
        return null;
    }

    @Override
    public void clear() {
        redisTemplate = getRedisTemplate();
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        redisTemplate = getRedisTemplate();
        Long size = redisTemplate.opsForHash().size(id);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}