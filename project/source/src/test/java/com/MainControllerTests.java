package com;

import clientserver.Client;
import controller.MainPageController;
import model.dto.DrugDto;
import model.entity.Drug;
import model.entity.ShoppingCart;
import model.entity.User;
import org.junit.jupiter.api.Test;
import view.IMainPageView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MainControllerTests {
    @Test
    public void notEnoughStocksBuyDrug() throws IOException, ClassNotFoundException {
        DrugDto drugDto=mock(DrugDto.class);
        when(drugDto.getName()).thenReturn("drug");
        when(drugDto.getPrice()).thenReturn("3");
        when(drugDto.getType()).thenReturn("whatever");
        Drug d=mock(Drug.class);
        IMainPageView iMainPageView= mock(IMainPageView.class);
        Client.Connection conn= mock(Client.Connection.class);
        when(iMainPageView.getConnection()).thenReturn(conn);
        when(conn.getMed("drug")).thenReturn(d);
        when(d.getStock()).thenReturn(2);
        when(iMainPageView.getQuantity()).thenReturn("3");


        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.addToCart(drugDto);

        verify(iMainPageView).setInfo("Not enough stocks");
    }

    @Test
    public void insufficientFundsBuyDrug() throws IOException, ClassNotFoundException {
        DrugDto drugDto=mock(DrugDto.class);
        when(drugDto.getName()).thenReturn("drug");
        when(drugDto.getPrice()).thenReturn("3");
        when(drugDto.getType()).thenReturn("whatever");
        Drug d=mock(Drug.class);
        User u=mock(User.class);
        IMainPageView iMainPageView= mock(IMainPageView.class);
        Client.Connection conn= mock(Client.Connection.class);
        when(iMainPageView.getConnection()).thenReturn(conn);
        when(conn.getMed("drug")).thenReturn(d);
        when(d.getStock()).thenReturn(2);
        when(iMainPageView.getQuantity()).thenReturn("2");
        when(iMainPageView.getUser()).thenReturn(u);
        when(u.getMoney()).thenReturn(5.0f);
        when(drugDto.getQuantityDiscounted()).thenReturn("0");
        when(drugDto.getDiscount()).thenReturn("0");


        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.addToCart(drugDto);

        verify(iMainPageView).setInfo("Insufficient funds");
    }

    @Test
    public void addedSuccessfullyBuyDrug() throws IOException, ClassNotFoundException {
        DrugDto drugDto=mock(DrugDto.class);
        when(drugDto.getName()).thenReturn("drug");
        when(drugDto.getPrice()).thenReturn("3");
        when(drugDto.getType()).thenReturn("whatever");
        Drug d=mock(Drug.class);
        User u=mock(User.class);
        IMainPageView iMainPageView= mock(IMainPageView.class);
        Client.Connection conn= mock(Client.Connection.class);
        when(iMainPageView.getConnection()).thenReturn(conn);
        when(conn.getMed("drug")).thenReturn(d);
        when(d.getStock()).thenReturn(2);
        when(iMainPageView.getQuantity()).thenReturn("2");
        when(iMainPageView.getUser()).thenReturn(u);
        when(u.getMoney()).thenReturn(100.0f);
        when(drugDto.getQuantityDiscounted()).thenReturn("0");
        when(drugDto.getDiscount()).thenReturn("0");

        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.addToCart(drugDto);

        verify(iMainPageView).setInfo("Added successfully");
    }

    @Test
    public void boughtSuccessfully() throws IOException {
        IMainPageView iMainPageView= mock(IMainPageView.class);
        Client.Connection conn= mock(Client.Connection.class);
        ShoppingCart set= mock(ShoppingCart.class);
        when(iMainPageView.getConnection()).thenReturn(conn);
        Set<ShoppingCart> s=new HashSet<ShoppingCart>();
        s.add(set);
        when(iMainPageView.getShoppingCart()).thenReturn(s);

        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.buy();

        verify(iMainPageView).setInfo("Bought Successfully");
    }

    @Test
    public void addRatingUnsuccessfully() throws IOException {
        IMainPageView iMainPageView= mock(IMainPageView.class);
        when(iMainPageView.getRating()).thenReturn("7");
        DrugDto d=mock(DrugDto.class);

        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.addRating(d);

        verify(iMainPageView).setInfo("Rating must be in 1-5 interval");
    }

    @Test
    public void addRatingSuccessfully() throws IOException {
        IMainPageView iMainPageView= mock(IMainPageView.class);
        Client.Connection conn= mock(Client.Connection.class);
        when(iMainPageView.getRating()).thenReturn("4");
        when(iMainPageView.getConnection()).thenReturn(conn);
        DrugDto d=mock(DrugDto.class);

        MainPageController mainPageController=new MainPageController(iMainPageView);
        mainPageController.addRating(d);

        verify(iMainPageView).setInfo("Rating submited successfully");
    }
}
