package com;

import clientserver.Client;
import org.junit.jupiter.api.Test;
import view.ILoginView;
import controller.LoginController;


import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginControllerTests {
   @Test
    public void givenAdminUsernameAndPasswordLoginShowAdminView() throws IOException, ClassNotFoundException {
        ILoginView loginView=mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("admin");
        when(loginView.getPassword()).thenReturn("admin");

        LoginController controller=new LoginController(loginView);
        controller.login();

        verify(loginView).showAdminView();
    }

    @Test
    public void givenInvalidUsernameAndPasswordLoginShowErrorMessage() throws IOException, ClassNotFoundException {
        Client.Connection connection = mock(Client.Connection.class);

        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("orice");
        when(loginView.getPassword()).thenReturn("nimic");
        when(loginView.getConnection()).thenReturn(connection);
        when(loginView.getConnection().checkUser(loginView.getUsername(),loginView.getPassword())).thenReturn(false);

        LoginController controller = new LoginController(loginView);

        controller.login();

        verify(loginView).showErrorMessage("invalid Username/Password");
    }

    @Test
    public void givenCorrectUsernameAndPasswordLoginShowMainView() throws IOException, ClassNotFoundException {
        Client.Connection connection = mock(Client.Connection.class);

        ILoginView loginView = mock(ILoginView.class);
        when(loginView.getUsername()).thenReturn("correctUsername");
        when(loginView.getPassword()).thenReturn("andPassword");
        when(loginView.getConnection()).thenReturn(connection);
        when(loginView.getConnection().checkUser(loginView.getUsername(), loginView.getPassword())).thenReturn(true);

        LoginController controller = new LoginController(loginView);

        controller.login();

        verify(loginView).showMainView();
    }
}
