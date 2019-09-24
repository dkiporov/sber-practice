package ru.sbrf.practice.transaction;

import ru.sbrf.practice.transaction.dao.MentorDao;
import ru.sbrf.practice.transaction.entity.Mentor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;

import static ru.sbrf.practice.transaction.Constants.PASS;
import static ru.sbrf.practice.transaction.Constants.USER;

public class MySqlConnectionExample {
    public static void main(String[] args) throws Exception {
//        jdbcExample();
        hibernateExample();

    }

    private static void jdbcExample() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", USER, PASS);
        Statement insertStatement = connection.createStatement();
        insertStatement.execute("insert into mentor(id, name) values(30,'Алексей');");

//        PreparedStatement selectStatement = connection.prepareStatement("select * from mentor where name like ?");
//        selectStatement.setString(1," ");
//        selectStatement.execute();
//        ResultSet resultSet = selectStatement.getResultSet();
//        while (resultSet.next()){
//            System.out.println("Ментор с именем " + resultSet.getString(2));
//        }

        connection.close();
    }

    private static void hibernateExample() throws InterruptedException {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("ru.sbrf.practice.transaction.entity");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        MentorDao mentorDao = new MentorDao(entityManager);

//        Mentor mentor = new Mentor("");
//        mentorDao.create(mentor);
//        mentorDao.create(new Mentor(""));
//        System.out.println(mentorDao.readAll());

        for(int i = 0; i < 5; i++) {
            Thread.sleep(2000L);
            System.out.println(mentorDao.readAll().size());
        }
//
//        entityManager.getTransaction().begin();
//        entityManager.getTransaction().commit();
        System.out.println("Ожидаем 10 сек");
        Thread.sleep(10000L);
        System.out.println(mentorDao.readAll().size());

        entityManager.close();
    }
}
