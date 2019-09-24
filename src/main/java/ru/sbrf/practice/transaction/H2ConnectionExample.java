package ru.sbrf.practice.transaction;

import org.h2.jdbc.JdbcConnection;
import ru.sbrf.practice.transaction.dao.MentorDao;
import ru.sbrf.practice.transaction.entity.Mentor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2ConnectionExample {
    public static void main(String[] args) throws Exception {
        JdbcConnection jdbcConnection = (JdbcConnection) DriverManager.getConnection("jdbc:h2:mem:test");
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("ru.sbrf.practice.transaction.entity");

        Statement statement = jdbcConnection.createStatement();
        statement.execute("SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;");
        statement.execute("START TRANSACTION; INSERT INTO mentor VALUES (NULL, 'Чукча');");

        EntityManager em = entityManagerFactory.createEntityManager();

        MentorDao mentorDao = new MentorDao(em);

        em.getTransaction().begin();
        em.persist(new Mentor(""));
        em.getTransaction().commit();

        System.out.println(mentorDao.readAll());
        em.close();
    }
}
