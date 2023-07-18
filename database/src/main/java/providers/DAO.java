package providers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import services.HibernateUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DAO<T> {
    private final Class<T> type;

    public DAO(Class<T> type) {
        this.type = type;
    }

    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
        }
    }

    public T read(Serializable id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
        }
    }

    public void delete(Serializable id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(type, id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
        }
    }

    public List<T> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM " + type.getSimpleName(), type).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printAll() {
        try (Session session = HibernateUtil.getSession()) {
            List<T> entities = session.createQuery("FROM " + type.getSimpleName(), type).list();
            System.out.println(Arrays.toString(entities.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM " + type.getSimpleName()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
        }
    }
}