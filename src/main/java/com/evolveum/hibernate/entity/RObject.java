package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.Constants;
import com.evolveum.hibernate.util.EntityState;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "m_object")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class RObject implements EntityState {

    private Boolean trans;

    private String oid;

    private String name;

    private Set<RAssignment> assignments;

    // more fields, omitted for brevity

    @Id
    @GeneratedValue(generator = "ObjectOidGenerator")
    @GenericGenerator(name = "ObjectOidGenerator", strategy = "com.evolveum.hibernate.util.ObjectOidGenerator")
    @Column(name = "oid", nullable = false, updatable = false, length = Constants.COLUMN_LENGTH_OID)
    public String getOid() {
        return oid;
    }

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = ALL)
    public Set<RAssignment> getAssignments() {
        if (assignments == null) {
            assignments = new HashSet<>();
        }
        return assignments;
    }

    public void setAssignments(Set<RAssignment> assignments) {
        this.assignments = assignments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Transient
    @Override
    public Boolean isTransient() {
        return trans;
    }

    @Override
    public void setTransient(Boolean trans) {
        this.trans = trans;
    }
}
