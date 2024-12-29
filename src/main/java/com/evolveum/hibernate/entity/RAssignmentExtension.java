package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.Constants;
import com.evolveum.hibernate.util.EntityState;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(RAssignmentExtensionId.class)
@Table(name = "m_assignment_extension")
public class RAssignmentExtension implements Serializable, EntityState {

    private Boolean trans;

    private RAssignment owner;
    private String ownerOid;
    private Integer ownerId;

    // there are multiple fields instead of this placeholder value (eg. Set)
    private String value;

    @Transient
    @Override
    public Boolean isTransient() {
        return trans;
    }

    @Override
    public void setTransient(Boolean trans) {
        this.trans = trans;
    }

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(
            value = {
                    @JoinColumn(name = "owner_owner_oid", referencedColumnName = "owner_oid", nullable = false),
                    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
            },
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    public RAssignment getOwner() {
        return owner;
    }

    /*
     * oid is taken from owner object (RAssignment)
     */
    @Id
    @Column(name = "owner_owner_oid", length = Constants.COLUMN_LENGTH_OID)
    public String getOwnerOid() {
        if (ownerOid == null && owner != null) {
            ownerOid = owner.getOwnerOid();
        }
        return ownerOid;
    }

    @Id
    @Column(name = "owner_id", length = Constants.COLUMN_LENGTH_OID)
    public Integer getOwnerId() {
        if (ownerId == null && owner != null) {
            ownerId = owner.getId();
        }
        return ownerId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setOwnerOid(String ownerOid) {
        this.ownerOid = ownerOid;
    }

    public void setOwner(RAssignment owner) {
        this.owner = owner;
        if (owner != null && owner.getExtension() != this) {
            owner.setExtension(this);
        }
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
