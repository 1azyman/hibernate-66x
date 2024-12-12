package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.Constants;
import com.evolveum.hibernate.util.EntityState;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@IdClass(RAssignmentExtensionId.class)
@Table(name = "m_assignment_extension")
public class RAssignmentExtension implements Serializable, EntityState {

    private Boolean trans;

    private RAssignment owner;
    private String ownerOid;
    private Integer ownerId;

    private Set<RAExtString> strings;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = RAExtValue.ANY_CONTAINER, orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<RAExtString> getStrings() {
        if (strings == null) {
            strings = new HashSet<>();
        }
        return strings;
    }

    public void setOwnerOid(String ownerOid) {
        this.ownerOid = ownerOid;
    }

    public void setStrings(Set<RAExtString> strings) {
        this.strings = strings;
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

        RAssignmentExtension that = (RAssignmentExtension) o;

        if (strings != null ? !strings.equals(that.strings) : that.strings != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
