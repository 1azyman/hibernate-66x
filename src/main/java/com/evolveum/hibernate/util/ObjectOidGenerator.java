package com.evolveum.hibernate.util;

import com.evolveum.hibernate.entity.RObject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class ObjectOidGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        RObject obj = (RObject) object;
        if (StringUtils.isNotEmpty(obj.getOid())) {
            return obj.getOid();
        }

        return UUID.randomUUID().toString();
    }
}
