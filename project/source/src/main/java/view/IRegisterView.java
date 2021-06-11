package view;

import clientserver.Client;

public interface IRegisterView {
    String getUsername();
    String getPassword();
    String getEmail();
    String getName();
    Client.Connection getConnection();
    void setInfo(String message);

    void resetFields();
}
