package ge.edu.freeuni.providers;

import ge.edu.freeuni.services.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<T> getByField(String fieldName, Serializable value) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM " + type.getSimpleName() + " WHERE " + fieldName + " = :value";
            Query<T> query = session.createQuery(hql, type);
            query.setParameter("value", value);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> getByFields(String[] fieldNames, Serializable[] values) {
        try (Session session = HibernateUtil.getSession()) {
            if (fieldNames.length != values.length) {
                throw new IllegalArgumentException("Field names and values must have the same length.");
            }

            StringBuilder hql = new StringBuilder("FROM " + type.getSimpleName() + " WHERE ");
            for (int i = 0; i < fieldNames.length; i++) {
                if (i > 0) {
                    hql.append(" AND ");
                }
                hql.append(fieldNames[i]).append(" = :value").append(i);
            }

            Query<T> query = session.createQuery(hql.toString(), type);
            for (int i = 0; i < values.length; i++) {
                query.setParameter("value" + i, values[i]);
            }

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}