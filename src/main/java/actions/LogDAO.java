package actions;

import Entity.LogEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LogDAO { //класс для работы с бд
    public static LogEntity findById(int id) {
        return LogTest.getSessionFactory().openSession().get(LogEntity.class, id);
    }
    public static void saveLog(LogEntity log){
        Session session = LogTest.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(log);
        tx1.commit();
        session.close();
    }
    public static void delete(LogEntity log) {
        Session session = LogTest.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(log);
        tx1.commit();
        session.close();
    }
    public static List<LogEntity> findAll() {
        List<LogEntity> logs = (List<LogEntity>)  LogTest.getSessionFactory().openSession().createQuery("from LogEntity").list();
        return logs;
    }

    public static int max(){
        int max = (int) (LogTest.getSessionFactory().openSession().createQuery("SELECT Max(id) from LogEntity").list().get(0) == null ?
                0 : LogTest.getSessionFactory().openSession().createQuery("SELECT Max(id) from LogEntity").list().get(0));
        return max;

    }
}
