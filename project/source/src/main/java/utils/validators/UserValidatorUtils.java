package utils.validators;

import model.repository.UsersRepo;

import view.ILoginView;
import view.IRegisterView;


public final class UserValidatorUtils {

    private UserValidatorUtils(){}

    public static void validateUserNullOrEmptyTextFields(IRegisterView regController, String username, String password, String nume, String email) {
        if (username == null || username.equals("")) {
            regController.setInfo("Username cannot be empty");
        }
        if (password == null || password.equals("")) {
            regController.setInfo("Password cannot be empty");

        }
        if (nume == null || nume.equals("")) {
            regController.setInfo("Name cannot be empty");
        }
        if (email == null || email.equals("")) {
            regController.setInfo("Email cannot be empty");
        }
    }

    public static void validateUserLength (IRegisterView regController, String username, String password){
            if (username.length() < 8) {
                regController.setInfo("Username must be at least 8 characters");

            }

            if (password.length() < 8) {
                regController.setInfo("Password must be at least 8 characters");

            }
    }

    public static void validateUserExist(IRegisterView registerView,UsersRepo userRepo,String username,String email){
        if(userRepo.checkUsername(username)){
            registerView.setInfo("Username already exists");

        }

        if (userRepo.checkEmail(email)) {
            registerView.setInfo("Email already exists");

        }
    }


    public static void validateLoginNullOrEmpty (ILoginView loginController, String username, String
            password){
        if (username == null || username.equals("")) {
            loginController.setInfo("Username cannot be empty");

        }

        if (password == null || password.equals("")) {
            loginController.setInfo("Password cannot be empty");

        }
    }

}

