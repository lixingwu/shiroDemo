package com.example.shirodemo.config.shior;

import com.example.shirodemo.config.component.RedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * RedisSessionDAO 使用redis操作shior的SessionDAO
 *
 * @author lixingwu
 */
public class RedisSessionDAO extends AbstractSessionDAO {
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    private String keyPrefix = "shiro_redis_session:";
    private RedisUtils redisManager;

    public RedisSessionDAO() {
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.info(">>> RedisSessionDAO update：", session);
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        logger.info(">>> RedisSessionDAO delete：", session);
        if (session != null && session.getId() != null) {
            this.redisManager.del(this.getKey(session.getId()));
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.info(">>> RedisSessionDAO getActiveSessions");
        Set<Session> sessions = new HashSet<>(100);
        Set<String> keys = this.redisManager.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {

            for (Object key : keys) {
                Session s = (Session) this.redisManager.get(key.toString());
                sessions.add(s);
            }
        }
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        logger.info(">>> RedisSessionDAO doCreate：", session);
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.info(">>> RedisSessionDAO doReadSession：", sessionId);
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            String key = this.getKey(sessionId);
            return (Session) this.redisManager.get(key);
        }
    }

    private String getKey(Serializable sessionId) {
        return this.keyPrefix + sessionId;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        logger.info(">>> RedisSessionDAO saveSession：", session);
        if (session != null && session.getId() != null) {
            redisManager.set(getKey(session.getId()), session, session.getTimeout());
        } else {
            logger.error("session or session id is null");
        }
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public void setRedisManager(RedisUtils redisManager) {
        this.redisManager = redisManager;
    }
}
