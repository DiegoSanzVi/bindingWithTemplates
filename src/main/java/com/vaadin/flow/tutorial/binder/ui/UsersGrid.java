package com.vaadin.flow.tutorial.binder.ui;

import java.util.Comparator;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.tutorial.binder.data.User;
import com.vaadin.flow.tutorial.binder.data.UsersRepository;

/**
 * UsersGrid it uses to display users in the system.
 */
public class UsersGrid extends Grid<User> {

    /**
     * DataProvider of the grid.
     */
    private ListDataProvider<User> dataProvider;

    public UsersGrid() {
        addColumn(User::getEmail).setHeader("email")
                .setComparator(Comparator.comparing(User::getEmail)).setSortable(true);
        addColumn(User::getFirstName).setHeader("first name")
                .setComparator(Comparator.comparing(User::getFirstName)).setSortable(true);
        addColumn(User::getLastName).setHeader("last name")
                .setComparator(Comparator.comparing(User::getLastName)).setSortable(true);

        setSelectionMode(Grid.SelectionMode.SINGLE);

        initDataProvider();
    }

    private void initDataProvider() {
        dataProvider = DataProvider.ofCollection(UsersRepository.getUsers());
        setDataProvider(dataProvider);
    }

    public void refreshAll() {
        dataProvider.refreshAll();
    }

    /**
     * Refresh a user in the grid.
     *
     * @param user user
     */
    public void refresh(User user) {
        dataProvider.refreshItem(user);
    }
}
