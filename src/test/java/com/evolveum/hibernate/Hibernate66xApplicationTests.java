package com.evolveum.hibernate;

import com.evolveum.hibernate.entity.RAssignment;
import com.evolveum.hibernate.entity.RUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class Hibernate66xApplicationTests {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testMergeAssertionError() {
        String oid = UUID.randomUUID().toString();
        String name = "first";
        String assignmentValue = "some value";


        RUser user = createUser(oid, name, assignmentValue);
        user.setTransient(true);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        RUser other = createUser(oid, "second", "some value");

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(other);
        em.getTransaction().commit();
        em.close();
    }

    private RUser createUser(String oid, String name, String assignmentValue) {
        RUser user = new RUser();
        user.setOid(oid);
        user.setName(name);

        RAssignment assignment = new RAssignment();
        assignment.setOwner(user);
        assignment.setId(1);
        assignment.setSomeValue(assignmentValue);

        user.getAssignments().add(assignment);

        return user;
    }
}
