package com.devremulk.web.base;

import com.devremulk.web.audit.BaseEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity {
    @Column(name = "created_user", nullable = false)
    private String createdUser;

    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDatetime;

    @Column(name = "update_user", nullable = false)
    private String updateUser;

    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updateDatetime;

    @Column(name = "remove_user")
    private String removeUser;

    @Column(name = "remove_datetime")
    private LocalDateTime removeDatetime;

    @Column(name = "deleted")
    private Boolean deleted;

    @Version
    private Long version;

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemoveUser() {
        return removeUser;
    }

    public void setRemoveUser(String removeUser) {
        this.removeUser = removeUser;
    }

    public LocalDateTime getRemoveDatetime() {
        return removeDatetime;
    }

    public void setRemoveDatetime(LocalDateTime removeDatetime) {
        this.removeDatetime = removeDatetime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
