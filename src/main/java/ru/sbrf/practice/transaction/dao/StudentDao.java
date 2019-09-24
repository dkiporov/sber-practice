package ru.sbrf.practice.transaction.dao;

import ru.sbrf.practice.transaction.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StudentDao {

    private EntityManager entityManager;

    public StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Student create(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
        entityManager.close();
        return student;
    }

    public Student read(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        transaction.commit();
        entityManager.close();
        return student;
    }
//
//    public Student update(Student student) {
//
//    }
//
//    public void delete(Student student) {
//
//    }
}
