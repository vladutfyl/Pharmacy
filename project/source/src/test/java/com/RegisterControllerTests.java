package com;

import clientserver.Client;
import controller.RegisterController;
import org.junit.jupiter.api.Test;
import view.IRegisterView;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegisterControllerTests {
    @Test
    public void invalidLengthUsername() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);

        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("ss");
        when(iRegisterView.getPassword()).thenReturn("whatever");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);


        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Username must be at least 8 characters");
    }

    @Test
    public void invalidLengthPassword() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("username");
        when(iRegisterView.getPassword()).thenReturn("ss");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Password must be at least 8 characters");
    }

    @Test
    public void usernameMissing() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("");
        when(iRegisterView.getPassword()).thenReturn("whatever");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Username cannot be empty");
    }

    @Test
    public void passwordMissing() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("username");
        when(iRegisterView.getPassword()).thenReturn("");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Password cannot be empty");
    }

    @Test
    public void nameMissing() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("username");
        when(iRegisterView.getPassword()).thenReturn("whatever");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Name cannot be empty");
    }

    @Test
    public void emailMissing() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("username");
        when(iRegisterView.getPassword()).thenReturn("whatever");
        when(iRegisterView.getEmail()).thenReturn("");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Email cannot be empty");
    }

    @Test
    public void registerSuccessfully() throws IOException {
        Client.Connection connection = mock(Client.Connection.class);
        IRegisterView iRegisterView =mock(IRegisterView.class);
        when(iRegisterView.getUsername()).thenReturn("username");
        when(iRegisterView.getPassword()).thenReturn("whatever");
        when(iRegisterView.getEmail()).thenReturn("email");
        when(iRegisterView.getName()).thenReturn("name");
        when(iRegisterView.getConnection()).thenReturn(connection);

        RegisterController controller=new RegisterController(iRegisterView);
        controller.register();

        verify(iRegisterView).setInfo("Registered succesfully");
    }

}
