package ru.sbrf.practice.transaction.dao;

import ru.sbrf.practice.transaction.entity.Mentor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MentorDao {

    private EntityManager entityManager;

    public MentorDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Mentor read(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Mentor mentor = entityManager.find(Mentor.class, id);
        transaction.commit();
        entityManager.close();
        return mentor;
    }

    public List<Mentor> readAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        List<Mentor> mentors = entityManager
                .createQuery("SELECT m FROM Mentor m") // select * from mentor
                .getResultList();
//        transaction.commit();
        return mentors;
    }

    public Mentor create(Mentor mentor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mentor);
        transaction.commit();
        return mentor;
    }

}
