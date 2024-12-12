package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.Constants;
import jakarta.persistence.*;
import org.hibernate.annotations.ForeignKey;

import java.util.Objects;

@Entity
@IdClass(RAExtStringId.class)
@Table(name = "m_assignment_ext_string")
public class RAExtString extends RAExtBase<String> implements RAExtValue<String> {

    private String value;

    public RAExtString() {
    }

    public RAExtString(String value) {
        this.value = value;
    }

    @ForeignKey(name = "fk_a_ext_string_owner")
    @MapsId("owner")
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumns({
            @PrimaryKeyJoinColumn(name = "anyContainer_owner_owner_oid", referencedColumnName = "owner_owner_oid"),
            @PrimaryKeyJoinColumn(name = "anyContainer_owner_id", referencedColumnName = "owner_id")
    })
    public RAssignmentExtension getAnyContainer() {
        return super.getAnyContainer();
    }

    @Id
    @Column(name = "anyContainer_owner_owner_oid", length = Constants.COLUMN_LENGTH_OID)
    public String getOwnerOid() {
        return super.getOwnerOid();
    }

    @Id
    @Column(name = "anyContainer_owner_id")
    public Integer getOwnerId() {
        return super.getOwnerId();
    }

    @Id
    @Column(name = "item_id")
    public Integer getItemId() {
        return super.getItemId();
    }

    @Column(name = "stringValue")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RAExtString that = (RAExtString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public RAExtStringId createId() {
        return RAExtStringId.createFromValue(this);
    }
}
