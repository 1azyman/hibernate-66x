package com.evolveum.hibernate.entity;

import java.io.Serializable;
import java.util.Objects;

public class RAExtBaseId implements Serializable {

    protected String ownerOid;
    protected Integer ownerId;
    protected Integer itemId;

    void fillInFromValue(RAExtBase value) {
        ownerOid = value.getOwnerOid();
        ownerId = value.getOwnerId();
        itemId = value.getItemId();
    }

    public String getOwnerOid() {
        return ownerOid;
    }

    public void setOwnerOid(String ownerOid) {
        this.ownerOid = ownerOid;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RAExtBaseId))
            return false;
        RAExtBaseId that = (RAExtBaseId) o;
        return Objects.equals(itemId, that.itemId) &&
                Objects.equals(ownerOid, that.ownerOid) &&
                Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerOid, ownerId, itemId);
    }
}
