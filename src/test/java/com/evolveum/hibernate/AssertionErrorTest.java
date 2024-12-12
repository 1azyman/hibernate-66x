package com.evolveum.hibernate;

import com.evolveum.hibernate.entity.RAssignment;
import com.evolveum.hibernate.entity.RUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class AssertionErrorTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testMergeAssertionError() {
        final String oid = UUID.randomUUID().toString();
        final String name = "first";
        final String assignmentValue = "some value";

        // first operation, add user

        RUser user = createUser(oid, name, assignmentValue);
        user.setTransient(true);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        RUser added = getUser(oid);
        assertUser(added, name, assignmentValue);

        // second operation, happens sometime later

        final String newName = "second";
        RUser other = createUser(oid, newName, "some value");

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(other);
        em.getTransaction().commit();
        em.close();

        RUser merged = getUser(oid);
        assertUser(merged, newName, assignmentValue);
    }

    private void assertUser(RUser user, String name, String assignmentValue) {
        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(assignmentValue, user.getAssignments().iterator().next().getSomeValue());
        Assertions.assertEquals(1, user.getAssignments().size());
        Assertions.assertNull(user.getAssignments().iterator().next().getExtension());
    }

    private RUser getUser(String oid) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        RUser user = em.find(RUser.class, oid);
        em.getTransaction().commit();

        return user;
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
