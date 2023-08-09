package providers;

import entities.User;
import org.hibernate.Session;
import services.HibernateUtil;

public class UserDao {


    public void registerUser(User user) {
        DAO<User> dao  = DAOFactory.getInstance().getDAO(User.class);
        dao.create(user);
    }

    public boolean usernameExists(String username) {
        try(Session session = HibernateUtil.getSession()){
            User user = (User)session.createQuery("SELECT u FROM User u " +
                    " WHERE u.username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            return user != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User getUserEntity(String username) {
        try(Session session = HibernateUtil.getSession()){
            User user = (User)session.createQuery("SELECT u FROM User u " +
                            " WHERE u.username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
