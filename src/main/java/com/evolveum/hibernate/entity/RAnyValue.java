package com.evolveum.hibernate.entity;

import com.evolveum.hibernate.util.EntityState;

import java.io.Serializable;

public interface RAnyValue<T> extends Serializable, EntityState {

    String F_VALUE = "value";

    String F_ITEM_ID = "itemId";

    Integer getItemId();

    void setItemId(Integer id);

    T getValue();

    Serializable createId();
}
