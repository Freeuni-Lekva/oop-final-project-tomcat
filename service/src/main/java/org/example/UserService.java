package org.example;

import entities.User;
import providers.UserDao;
import services.PasswordUtils;

public class UserService {
    private final UserDao userDao = new UserDao();

    public boolean usernameExists(String username) {

        return userDao.usernameExists(username.toLowerCase());
    }

    public void addAccount(User newUser){
        userDao.registerUser(newUser);
    }

    public User getUserEntity(String username, String password) {
        User user = userDao.getUserEntity(username);
        String hashedProvidedPassword = PasswordUtils.getPasswordCode(password,user.getSalt());
        if(hashedProvidedPassword.equals(user.getPasswordHash())){
            return user;
        }else{
            return null;
        }
    }

    public boolean account_exists(String username) {
        return userDao.getUserEntity(username) != null;
    }


}
