package model.service;


import model.entity.User;
import model.repository.UsersRepo;
import utils.UuidGeneratorUtils;


import java.util.List;


public class UserService {
    private final UsersRepo userRepo;

    public UserService() {
        userRepo = new UsersRepo();
    }

    public void addUser( String username, String password, String nume, String email){

        User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(nume);
        user.setIdUser(UuidGeneratorUtils.generateUUID());

        userRepo.insertUser(user);
    }

    public boolean checkUser(String username,String password){
        return userRepo.checkLogin(username,password);
    }



    public UsersRepo getUserRepo() {
        return userRepo;
    }

    public List<User> getUsers() {
        return userRepo.getUsers();
    }

    public User getCurrentUser(String username) {
        return userRepo.getCurrentUser(username);
    }

    public void deleteUser(User currentUser) {
        userRepo.deleteUser(currentUser);
    }


    public void modifyUser(String username, String email, String password, String name,float money) {
        userRepo.updateUser(username,password,email,name,money);
    }
}
