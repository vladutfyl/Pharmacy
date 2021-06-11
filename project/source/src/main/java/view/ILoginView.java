package view;

import clientserver.Client;
import model.entity.User;

import java.io.IOException;

public interface ILoginView {
    String getUsername();
    String getPassword();
    User getUser();
    Client.Connection getConnection();

    void setUser(User u);
    void setInfo(String s);
    void showAdminView() throws IOException;
    void showErrorMessage(String message) throws IOException;

    void showMainView() throws IOException;
}
