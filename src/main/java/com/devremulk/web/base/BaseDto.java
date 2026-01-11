package com.devremulk.web.base;

import java.time.LocalDateTime;

public abstract class BaseDto {
    private String createdUser;
    private LocalDateTime createdDatetime;
    private String updateUser;
    private LocalDateTime updateDatetime;
    private String removeUser;
    private LocalDateTime removeDatetime;
    private Boolean deleted;
    private Long version;
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
