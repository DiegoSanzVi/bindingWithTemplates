package demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

public class CommentsGrid extends Grid<UserComment> {

    ListDataProvider<UserComment> dataProvider;

    public CommentsGrid() {
        addColumn(UserComment::getEmail);
        addColumn(UserComment::getFirstName);
        addColumn(UserComment::getLastName);

        setSelectionMode(Grid.SelectionMode.SINGLE);

        initDataProvider();
    }

    private void initDataProvider(){
        dataProvider = DataProvider.ofCollection(UsersCommentsRepository.getUserComments());
        setDataProvider(dataProvider);
    }

    public void refreshAll() {
        dataProvider.refreshAll();
    }

    public void refresh(UserComment userComment) {
        dataProvider.refreshItem(userComment);
    }
}
