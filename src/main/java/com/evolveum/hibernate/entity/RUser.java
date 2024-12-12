package com.evolveum.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table
@ForeignKey(name = "fk_user")
@DynamicUpdate
public class RUser extends RObject {

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
