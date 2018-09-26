package com.vaadin.flow.tutorial.binder.ui;

import java.util.Comparator;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.tutorial.binder.data.User;
import com.vaadin.flow.tutorial.binder.data.UsersRepository;

public class UsersGrid extends Grid<User> {

    ListDataProvider<User> dataProvider;

    public UsersGrid() {
        addColumn(User::getEmail).setHeader("email").setComparator(Comparator.comparing(User::getEmail)).setSortable(true);
        addColumn(User::getFirstName).setHeader("first name").setComparator(Comparator.comparing(User::getFirstName)).setSortable(true);
        addColumn(User::getLastName).setHeader("last name").setComparator(Comparator.comparing(User::getLastName)).setSortable(true);

        setSelectionMode(Grid.SelectionMode.SINGLE);

        initDataProvider();
    }

    private void initDataProvider(){
        dataProvider = DataProvider.ofCollection(UsersRepository.getUserComments());
        setDataProvider(dataProvider);
    }

    public void refreshAll() {
        dataProvider.refreshAll();
    }

    public void refresh(User userComment) {
        dataProvider.refreshItem(userComment);
    }
}
