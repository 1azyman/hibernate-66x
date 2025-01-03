package com.evolveum.hibernate.entity;

import java.io.Serializable;

public class RAssignmentExtensionId implements Serializable {

    private String ownerOid;
    private Integer ownerId;

    public RAssignmentExtensionId() {
    }

    public RAssignmentExtensionId(String ownerOid, Integer ownerId) {
        this.ownerOid = ownerOid;
        this.ownerId = ownerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RAssignmentExtensionId that = (RAssignmentExtensionId) o;

        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (ownerOid != null ? !ownerOid.equals(that.ownerOid) : that.ownerOid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ownerOid != null ? ownerOid.hashCode() : 0;
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RAssignmentExtensionId{" +
                "ownerOid='" + ownerOid + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
