package demo;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * The main view contains a button and a click listener.
 */
@Tag("main-view")
@HtmlImport("src/main-view.html")
@Route("")
public class MainView extends PolymerTemplate<TemplateModel> {


    @Id("formComponent")
    private FormComponent formComponent;

    @Id("vaadinGrid")
    private Grid<UserComment> commentsGrid;

    public MainView() {
        initGrid();
        initForm();
    }

    ListDataProvider<UserComment> dataProvider;
    List<UserComment> userCommentList;

    private void initGrid(){
        commentsGrid.addColumn(UserComment::getEmail);
        commentsGrid.addColumn(UserComment::getFirstName);
        commentsGrid.addColumn(UserComment::getLastName);

        commentsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        commentsGrid.addSelectionListener(selectionEvent -> {
            Optional<UserComment> selectedItem = selectionEvent.getFirstSelectedItem();
            if ( selectedItem.isPresent() ){
                formComponent.setUserComment(selectedItem.get());
            }
        });

        userCommentList = UserCommentRepository.getUserComments();
        dataProvider =  DataProvider.ofCollection(userCommentList);

        commentsGrid.setDataProvider(dataProvider);
    }

    private void initForm(){
        formComponent.addDeleteListener(()->{
            userCommentList.remove(commentsGrid.getSelectedItems().stream().findFirst().get());
            dataProvider.refreshAll();
        });

        formComponent.addSaveListener(()->{
           // commentsGrid.getDataProvider().refreshItem();
        });
    }
}