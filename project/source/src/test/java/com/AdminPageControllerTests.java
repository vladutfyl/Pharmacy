package com;

import clientserver.Client;
import controller.AdminController;
import model.dto.DrugDto;
import model.dto.UserDto;
import org.junit.jupiter.api.Test;
import view.IAdminView;



import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdminPageControllerTests {

    @Test
    public void addMedWrongPrice() throws IOException {
        IAdminView iAdminView =mock(IAdminView.class);
        when(iAdminView.getDrugName()).thenReturn("whatever");
        when(iAdminView.getPrice()).thenReturn("-5");
        when(iAdminView.getQuantity()).thenReturn("4");
        when(iAdminView.getType()).thenReturn("whatever");

        AdminController controller=new AdminController(iAdminView);
        controller.addMed();

        verify(iAdminView).setInfo("Invalid price");
    }

    @Test
    public void addMedWrongQuantity() throws IOException {
        IAdminView iAdminView =mock(IAdminView.class);
        when(iAdminView.getDrugName()).thenReturn("whatever");
        when(iAdminView.getPrice()).thenReturn("5");
        when(iAdminView.getQuantity()).thenReturn("-4");
        when(iAdminView.getType()).thenReturn("whatever");

        AdminController controller=new AdminController(iAdminView);
        controller.addMed();

        verify(iAdminView).setInfo("Invalid quantity");
    }

    @Test
    public void addMedWrongName() throws IOException {
        IAdminView iAdminView =mock(IAdminView.class);
        when(iAdminView.getDrugName()).thenReturn("");
        when(iAdminView.getPrice()).thenReturn("5");
        when(iAdminView.getQuantity()).thenReturn("4");
        when(iAdminView.getType()).thenReturn("whatever");

        AdminController controller=new AdminController(iAdminView);
        controller.addMed();

        verify(iAdminView).setInfo("Invalid name");
    }

    @Test
    public void userModifiedWithSuccess() throws IOException {
        IAdminView iAdminView =mock(IAdminView.class);
        when(iAdminView.getUsername()).thenReturn("3.5");
        when(iAdminView.getEmail()).thenReturn("gfg");
        when(iAdminView.getPassword()).thenReturn("asdf");
        when(iAdminView.getNume()).thenReturn("nume");
        UserDto user = mock(UserDto.class);
        Client.Connection conn = mock(Client.Connection.class);
        when(iAdminView.getConnection()).thenReturn(conn);
        when(user.getUsername()).thenReturn("asfag");

        AdminController controller=new AdminController(iAdminView);
        controller.modifyUser(user);

        verify(iAdminView).setInfo("User modified with success");
    }

    @Test
    public void invalidDiscount() throws IOException {
        IAdminView iAdminView =mock(IAdminView.class);
        when(iAdminView.getDiscount()).thenReturn("-1");
        when(iAdminView.getDrugName()).thenReturn("whatever");
        when(iAdminView.getPrice()).thenReturn("5");
        when(iAdminView.getQuantity()).thenReturn("-4");
        when(iAdminView.getType()).thenReturn("whatever");
        when(iAdminView.getRating()).thenReturn("4");
        when(iAdminView.getQuantityDiscounted()).thenReturn("3");
        DrugDto d=mock(DrugDto.class);

        AdminController controller=new AdminController(iAdminView);
        controller.modifyProduct(d);

        verify(iAdminView).setInfo("Discount must be in 0-100 interval");
    }

    @Test
    public void deleteUser() throws IOException, ClassNotFoundException {
        IAdminView iAdminView=mock(IAdminView.class);
        Client.Connection conn=mock(Client.Connection.class);
        UserDto u=mock(UserDto.class);
        when(iAdminView.getConnection()).thenReturn(conn);

        AdminController controller=new AdminController(iAdminView);
        controller.deleteUser(u);

        verify(iAdminView).setInfo("User "+ u.getUsername()+ " deleted successfully");
    }

    @Test
    public void deleteDrug() throws IOException {
        IAdminView iAdminView=mock(IAdminView.class);
        Client.Connection conn=mock(Client.Connection.class);
        DrugDto u=mock(DrugDto.class);
        when(iAdminView.getConnection()).thenReturn(conn);

        AdminController controller=new AdminController(iAdminView);
        controller.deleteDrug(u);

        verify(iAdminView).setInfo("Drug "+ u.getName()+ " deleted successfully");
    }
}
