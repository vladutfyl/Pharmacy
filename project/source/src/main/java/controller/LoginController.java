package controller;

import clientserver.Client;
import view.ILoginView;

import java.io.IOException;

public class LoginController {
    private final ILoginView iLoginView;
    public LoginController(ILoginView iLoginView){
        this.iLoginView=iLoginView;
    }

    public void login() throws IOException, ClassNotFoundException {
        String username = iLoginView.getUsername();
        String password = iLoginView.getPassword();
        Client.Connection conn=iLoginView.getConnection();

        if ("admin".equals(username) && "admin".equals(password)) {
            iLoginView.showAdminView();
        }
        else
            if(conn.checkUser(username,password))//if(userService.checkUser(username,password))
            {
                iLoginView.setUser(conn.getUser(username));//userService.getCurrentUser(username));
                iLoginView.showMainView();
            }
            else {
                iLoginView.showErrorMessage("invalid Username/Password");
            }

    }

}
