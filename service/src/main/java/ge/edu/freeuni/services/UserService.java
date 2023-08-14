package ge.edu.freeuni.services;

import ge.edu.freeuni.entities.User;
import ge.edu.freeuni.models.UserModel;
import ge.edu.freeuni.providers.DAO;
import ge.edu.freeuni.providers.DAOFactory;
import ge.edu.freeuni.responses.UserResponse;
import ge.edu.freeuni.util.EntityToModelBridge;
import ge.edu.freeuni.util.ModelToEntityBridge;

import java.util.List;

public class UserService {
    private DAO<User> userDAO = DAOFactory.getInstance().getDAO(User.class);

    public UserResponse findUser(String username) {
//        if (username == null || username.isEmpty()) {
//            return new UserResponse(false, "Username is empty", null);
//        }
//        try {
//            User users = getByUsername(username);
//            if (users == null) {
//                return new UserResponse(false, "User doesn't exist", null);
//            }
//            return new UserResponse(true, null, EntityToModelBridge.toUserModel(users));
//        } catch (RuntimeException e) {
//            return new UserResponse(false, e.getMessage(), null);
//        }
        return new UserResponse(true, null, new UserModel(1L, "styxbeneath", "saba", "khutsishvili", null));
    }

    public UserResponse findUser(Long id) {
        if (id == null) {
            return new UserResponse(false, "User identifier is empty", null);
        }
        try {
            User users = getById(id);
            if (users == null) {
                return new UserResponse(false, "User doesn't exist", null);
            }
            return new UserResponse(true, null, EntityToModelBridge.toUserModel(users));
        } catch (RuntimeException e) {
            return new UserResponse(false, e.getMessage(), null);
        }
    }

    public UserResponse findUser(String username, String password) {
        try {
            User user = getByUsername(username);
            if (user == null) {
                return new UserResponse(false, "User doesn't exist", null);
            }

            String hashedProvidedPassword = PasswordUtils.getPasswordCode(password, user.getSalt());
            if (hashedProvidedPassword.equals(user.getPasswordHash())) {
                return new UserResponse(true, null, EntityToModelBridge.toUserModel(user));
            } else {
                return new UserResponse(false, "Incorrect password", null);
            }
        } catch (RuntimeException e) {
            return new UserResponse(false, e.getMessage(), null);
        }
    }

    private User getByUsername(String username) throws RuntimeException {
        List<User> users = userDAO.getByField("username", username);
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    private User getById(Long id){
        List<User> users = userDAO.getByField("id",id);
        if(users==null || users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    public UserResponse addAccount(UserModel newUser) {
        if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty()
                || newUser.getFirstname().isEmpty() || newUser.getLastname().isEmpty()) {
            return new UserResponse(false, "Incorrect credentials", null);
        }

        if (accountExists(newUser.getUsername())) {
            return new UserResponse(false, "Username is occupied", null);
        }

        try {
            userDAO.create(ModelToEntityBridge.toUserEntity(newUser));
        } catch (RuntimeException e) {
            return new UserResponse(false, e.getMessage(), null);
        }
        return new UserResponse(true, null, newUser);
    }

    public boolean accountExists(String username) {
        return getByUsername(username) != null;
    }

    public void setUserDAO(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }
}
