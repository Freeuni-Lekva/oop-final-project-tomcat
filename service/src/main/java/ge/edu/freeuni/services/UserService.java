package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.responses.ServiceActionResponse;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.List;

public class UserService {
    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);

    public UserModel searchUser(String username) {
        User users = getByUsername(username);
        if (users == null) {
            return null;
        }
        return EntityToModelBridge.toUserModel(users);
    }

    private User getByUsername(String username) {
        List<User> users = userDAO.getByField("username", username);
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public ServiceActionResponse addAccount(UserModel newUser) {
        if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty()
                || newUser.getFirstname().isEmpty() || newUser.getLastname().isEmpty()) {
            return new ServiceActionResponse(false, "Incorrect credentials");
        }

        if (accountExists(newUser.getUsername())) {
            return new ServiceActionResponse(false, "Username is occupied");
        }

        userDAO.create(ModelToEntityBridge.toUserEntity(newUser));
        return new ServiceActionResponse(true, null);
    }

    public UserModel getUserEntity(String username, String password) {
        User user = getByUsername(username);
        if (user == null) {
            return null;
        }

        String hashedProvidedPassword = PasswordUtils.getPasswordCode(password, user.getSalt());
        if (hashedProvidedPassword.equals(user.getPasswordHash())) {
            return EntityToModelBridge.toUserModel(user);
        } else {
            return null;
        }
    }

    public boolean accountExists(String username) {
        return getByUsername(username) != null;
    }

    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }
}
