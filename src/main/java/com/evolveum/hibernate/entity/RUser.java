package com.evolveum.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "m_user")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_user"))
@DynamicUpdate
public class RUser extends RObject {

    private String fullName;

    // more fields, omitted for brevity

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
