package com.devremulk.web.audit;

import com.devremulk.web.base.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseEntityListener {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityListener.class);

    @PrePersist
    public void prePersist(BaseEntity entity) {
        String user = resolveUser();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        if (entity.getUuid() == null) {
            entity.setUuid(UUID.randomUUID().toString());
        }
        entity.setCreatedUser(user);
        entity.setCreatedDatetime(now);
        entity.setUpdateUser(user);
        entity.setUpdateDatetime(now);
        if (entity.getDeleted() == null) {
            entity.setDeleted(false);
        }
        logger.debug("Audit prePersist for entity {} by user {}", entity.getClass().getSimpleName(), user);
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        String user = resolveUser();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        entity.setUpdateUser(user);
        entity.setUpdateDatetime(now);
        if (Boolean.TRUE.equals(entity.getDeleted())) {
            if (entity.getRemoveUser() == null) {
                entity.setRemoveUser(user);
            }
            if (entity.getRemoveDatetime() == null) {
                entity.setRemoveDatetime(now);
            }
        }
        logger.debug("Audit preUpdate for entity {} by user {}", entity.getClass().getSimpleName(), user);
    }

    @PreRemove
    public void preRemove(BaseEntity entity) {
        String user = resolveUser();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        entity.setRemoveUser(user);
        entity.setRemoveDatetime(now);
        entity.setDeleted(true);
        logger.debug("Audit preRemove for entity {} by user {}", entity.getClass().getSimpleName(), user);
    }

    private String resolveUser() {
        String user = AuditContext.getCurrentUser();
        return user == null || user.isBlank() ? "system" : user;
    }
}
