package com.evolveum.hibernate.entity;

public interface RAExtValue<T> extends RAnyValue<T> {

    String ANY_CONTAINER = "anyContainer";

    RAssignmentExtension getAnyContainer();

    void setAnyContainer(RAssignmentExtension extension);
}
