package com.evolveum.hibernate.util;

import com.evolveum.hibernate.entity.RAssignment;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class ContainerIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof RAssignment a) {
            return generate(a);
        }

        throw new HibernateException("Couldn't create id for '" + object.getClass().getSimpleName() + "'.");
    }

    private Integer generate(RAssignment container) {
        if (container.getId() != null && container.getId() != 0) {
            return container.getId();
        }

        throw new RuntimeException("Unknown id, should not happen.");
    }
}
