package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.Constants;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@IdClass(RContainerId.class)
@Table(name = "m_assignment")
@DynamicUpdate
public class RAssignment {

    private RObject owner;
    private String ownerOid;
    private Integer id;

    private RAssignmentExtension extension;

    private String someValue;

    @JoinColumn(name = "owner_oid", referencedColumnName = "oid", foreignKey = @ForeignKey(name = "fk_assignment_owner"))
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    public RObject getOwner() {
        return owner;
    }

    @Id
    @Column(name = "owner_oid", length = Constants.COLUMN_LENGTH_OID, nullable = false)
    public String getOwnerOid() {
        if (owner != null && ownerOid == null) {
            ownerOid = owner.getOid();
        }
        return ownerOid;
    }

    @Id
    @GeneratedValue(generator = "ContainerIdGenerator")
    @GenericGenerator(name = "ContainerIdGenerator", strategy = "com.evolveum.hibernate.util.ContainerIdGenerator")
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})
    @JoinColumns(
            value = {
                    @JoinColumn(name = "extOid", referencedColumnName = "owner_owner_oid"),
                    @JoinColumn(name = "extId", referencedColumnName = "owner_id")
            },
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    public RAssignmentExtension getExtension() {
//        if (extension == null) {
//            extension = new RAssignmentExtension();
//            extension.setOwner(this);
//        }
        return extension;
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    public void setOwner(RObject owner) {
        this.owner = owner;
        if (owner != null) {
            setOwnerOid(owner.getOid());
        }
    }

    public void setOwnerOid(String ownerOid) {
        this.ownerOid = ownerOid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExtension(RAssignmentExtension extension) {
        this.extension = extension;
    }
}
