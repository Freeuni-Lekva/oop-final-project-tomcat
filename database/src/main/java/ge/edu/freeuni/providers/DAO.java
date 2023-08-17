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

    public Serializable create(T entity) throws RuntimeException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Serializable id = session.save(entity);
            transaction.commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public void bulkCreate(List<T> entities) throws RuntimeException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            for (T entity : entities) {
                session.save(entity);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (HibernateUtil.allowRollBack(transaction)) {
                transaction.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    public T read(Serializable id) throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(T entity) throws RuntimeException {
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
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Serializable id) throws RuntimeException {
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
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> getAll() throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM " + type.getSimpleName(), type).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
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

    public void clearTable() throws RuntimeException {
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
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> getByField(String fieldName, Serializable value) throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM " + type.getSimpleName() + " WHERE " + fieldName + " = :value";
            Query<T> query = session.createQuery(hql, type);
            query.setParameter("value", value);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> getByFields(String[] fieldNames, Serializable[] values, boolean isAnd) throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            if (fieldNames.length != values.length) {
                throw new IllegalArgumentException("Field names and values must have the same length.");
            }

            String operator = isAnd ? " AND " : " OR ";
            StringBuilder hql = new StringBuilder("FROM " + type.getSimpleName() + " WHERE ");
            for (int i = 0; i < fieldNames.length; i++) {
                if (i > 0) {
                    hql.append(operator);
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
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> getByFieldsAndOrdered(
            String[] fieldNames, Serializable[] values, String orderingFieldName, boolean desc, int maxResults) throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            if (fieldNames.length != values.length) {
                throw new IllegalArgumentException("Field names and values must have the same length.");
            }

            StringBuilder hql = new StringBuilder("FROM ").append(type.getSimpleName()).append(" WHERE ");
            for (int i = 0; i < fieldNames.length; i++) {
                if (i > 0) {
                    hql.append(" AND ");
                }
                hql.append(fieldNames[i]).append(" = :value").append(i);
            }

            hql.append(" ORDER BY ").append(orderingFieldName).append(desc ? " DESC" : " ASC");

            Query<T> query = session.createQuery(hql.toString(), type);
            for (int i = 0; i < values.length; i++) {
                query.setParameter("value" + i, values[i]);
            }
            query.setMaxResults(maxResults);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Object getByFieldsAndAggregate(String[] fieldNames, Serializable[] values, String aggregateFunction, String aggregateFieldName) throws RuntimeException {
        try (Session session = HibernateUtil.getSession()) {
            if (fieldNames.length != values.length) {
                throw new IllegalArgumentException("Field names and values must have the same length.");
            }

            StringBuilder hql = new StringBuilder("SELECT " + aggregateFunction + "(" + aggregateFieldName + ") ");
            hql.append("FROM ").append(type.getSimpleName()).append(" WHERE ");
            for (int i = 0; i < fieldNames.length; i++) {
                if (i > 0) {
                    hql.append(" AND ");
                }
                hql.append(fieldNames[i]).append(" = :value").append(i);
            }

            Query<Object> query = session.createQuery(hql.toString(), Object.class);
            for (int i = 0; i < values.length; i++) {
                query.setParameter("value" + i, values[i]);
            }
            List<Object> result = query.getResultList();
            return result == null || result.size() != 1 ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}