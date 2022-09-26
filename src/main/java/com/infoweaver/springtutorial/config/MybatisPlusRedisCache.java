package com.infoweaver.springtutorial.config;

import com.infoweaver.springtutorial.util.SpringUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Ruobing Shang 2022-09-25 21:15
 */
public class MybatisPlusRedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static RedisTemplate<String, Object> redisTemplate;
    private final String id;

    public MybatisPlusRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
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
            e.printStackTrace();
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

    /**
     * ERROR! The context of this bean cannot be updated when the context is updated!!!
     *
     * @return RedisTemplate
     */
    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> getRedisTemplate() {
        System.out.println("Redis's Application Context" + SpringUtils.getApplicationContext());
        return (RedisTemplate<String, Object>) SpringUtils.getBean("redisTemplate");
    }

}
