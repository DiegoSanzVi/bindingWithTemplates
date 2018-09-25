package demo;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
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
    private FormComponent commentForm;

    @Id("vaadinGrid")
    private CommentsGrid commentsGrid;

    public MainView() {
        commentsGrid.addSelectionListener(selectionEvent -> {
            Optional<UserComment> optionalUser = commentsGrid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()){
                commentForm.setBean(optionalUser.get());
                setEditionEnabled(true);
            }else{
                commentForm.removeBean();
                setEditionEnabled(false);
            }
        });

        initListeners();
    }

    private void initListeners(){
        FormButtonsBar formButtonsBar = commentForm.getActionButtons();

        formButtonsBar.addSaveListener(saveEvent -> {
            System.out.println("Saving: ");
            if ( ! commentForm.getBinder().validate().isOk()){
                return;
            }

            Optional<UserComment> optionalUserComment = commentForm.getBean();

            if ( optionalUserComment.isPresent() ){
                System.out.println(optionalUserComment.get());
                UserComment userComment = optionalUserComment.get();

                userComment = UsersCommentsRepository.save( userComment );

                commentsGrid.refreshAll();
                commentForm.setBean( userComment);
            }
        });

        formButtonsBar.addCancelListener(cancelEvent -> {
            commentsGrid.deselectAll();
        });

        formButtonsBar.addDeleteListener(deleteEvent -> {
            Optional<UserComment> optionalUserComment = commentsGrid.getSelectedItems().stream().findAny();

            if ( optionalUserComment.isPresent() ){
                UsersCommentsRepository.delete( optionalUserComment.get() );
                commentsGrid.refreshAll();
                commentForm.removeBean();
                commentsGrid.deselectAll();
            }
        });
    }

    public void setEditionEnabled(boolean enabled){
        commentForm.setEnabled(enabled);
    }
}