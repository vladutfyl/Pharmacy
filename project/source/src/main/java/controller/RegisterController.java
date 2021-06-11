package controller;

import utils.validators.UserValidatorUtils;
import view.IRegisterView;

import java.io.IOException;

public class RegisterController {
    private final IRegisterView iRegisterView;

    public RegisterController(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public void register() throws IOException {
        String username=iRegisterView.getUsername();
        String password=iRegisterView.getPassword();
        String email=iRegisterView.getEmail();
        String name=iRegisterView.getName();
        UserValidatorUtils.validateUserNullOrEmptyTextFields(iRegisterView,username,password,name,email);
        UserValidatorUtils.validateUserLength(iRegisterView,username,password);
        //UserValidator.validateUserExist(iRegisterView, userService.getUserRepo(), username,email);
        iRegisterView.getConnection().addUser(username,password, email, name);//userService.addUser(username,password,name,email);
        iRegisterView.resetFields();
        iRegisterView.setInfo("Registered succesfully");
    }
}
