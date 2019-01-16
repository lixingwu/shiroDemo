package com.example.shirodemo.config.shior;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author lixingwu
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
@Component("collectionRedisSessionDao")
@Lazy(false)
public class RedisSessionDAO extends AbstractSessionDAO {
    @Autowired
    private RedisTemplate redisTemplate;
    private String keyPrefix = "shiro_redis_session:";

    private String getKey(String originalKey) {
        return keyPrefix + originalKey;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.debug("更新seesion,id=[{}]", session.getId().toString());
        try {
            redisTemplate.opsForValue().set(getKey(session.getId().toString()), session, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Session session) {
        log.debug("删除seesion,id=[{}]", session.getId().toString());
        try {
            String key = getKey(session.getId().toString());
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info("获取存活的session");
        return Collections.emptySet();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.debug("创建seesion,id=[{}]", session.getId().toString());
        try {
            redisTemplate.opsForValue().set(getKey(session.getId().toString()), session, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {

        log.debug("获取seesion,id=[{}]", sessionId.toString());
        Session readSession = null;
        try {
            readSession = (Session) redisTemplate.opsForValue().get(getKey(sessionId.toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return readSession;
    }
}
