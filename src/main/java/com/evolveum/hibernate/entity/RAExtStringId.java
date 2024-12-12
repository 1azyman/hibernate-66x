package com.evolveum.hibernate.entity;

import java.util.Objects;

public class RAExtStringId extends RAExtBaseId {

    private String value;

    @Override
    public String getOwnerOid() {
        return super.getOwnerOid();
    }

    @Override
    public Integer getOwnerId() {
        return super.getOwnerId();
    }

    @Override
    public Integer getItemId() {
        return super.getItemId();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RAExtStringId))
            return false;
        if (!super.equals(o))
            return false;
        RAExtStringId that = (RAExtStringId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    public static RAExtStringId createFromValue(RAExtString value) {
        RAExtStringId rv = new RAExtStringId();
        rv.value = value.getValue();
        rv.fillInFromValue(value);
        return rv;
    }
}
