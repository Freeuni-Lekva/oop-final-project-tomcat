package ge.edu.freeuni.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private final Map<Class<?>, Object> services = new HashMap<>();
    private int numberOfServices;

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        if (!services.containsKey(serviceClass)) {
            try {
                T serviceInstance = serviceClass.getDeclaredConstructor().newInstance();
                services.put(serviceClass, serviceInstance);
                numberOfServices++;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error creating service instance", e);
            }
        }
        return (T) services.get(serviceClass);
    }

    private static ServiceFactory instance;

    private ServiceFactory() {
        numberOfServices = 0;
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }
}

