package com.evolveum.hibernate.util;

import org.hibernate.Interceptor;

public class EntityStateInterceptor implements Interceptor {

    @Override
    public Boolean isTransient(Object entity) {
        if (entity instanceof EntityState) {
            return isTransient((EntityState) entity);
        }

        return null;
    }

    private Boolean isTransient(EntityState object) {
        return isTransient(object, false);
    }

    @SuppressWarnings("SameParameterValue")
    private Boolean isTransient(EntityState object, boolean isObjectMyParent) {
        Boolean trans = object != null ? object.isTransient() : null;
        if (!isObjectMyParent) {
            return trans;
        }
        if (Boolean.TRUE.equals(trans)) {
            return true;
        }

        return null;
    }
}
