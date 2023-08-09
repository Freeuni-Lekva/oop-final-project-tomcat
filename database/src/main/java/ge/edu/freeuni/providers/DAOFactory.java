package ge.edu.freeuni.providers;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private final Map<Class<?>, DAO<?>> daoMap;

    private static DAOFactory instance;

    private DAOFactory() {
        daoMap = new HashMap<>();
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public synchronized <T> DAO<T> getDAO(Class<T> entityClass) {
        if (!daoMap.containsKey(entityClass)) {
            initializeDAO(entityClass);
        }
        return (DAO<T>) daoMap.get(entityClass);
    }

    private <T> void initializeDAO(Class<T> entityClass) {
        daoMap.put(entityClass, new DAO<>(entityClass));
    }
}