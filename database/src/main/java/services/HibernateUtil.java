package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            String RESOURCE = "hibernate.cfg.xml";
            configuration.configure(RESOURCE);
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static synchronized Session getSession() {
        if (SESSION_FACTORY != null) {
            return SESSION_FACTORY.openSession();
        } else {
            throw new RuntimeException("Hibernate error, session factory is not initialized");
        }
    }

    public static boolean allowRollBack(Transaction transaction) {
        return transaction != null && transaction.isActive() && transaction.getStatus().canRollback();
    }
}