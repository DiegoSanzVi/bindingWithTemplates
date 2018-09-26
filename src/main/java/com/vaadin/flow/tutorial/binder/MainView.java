package com.vaadin.flow.tutorial.binder;

import java.util.Optional;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.tutorial.binder.data.User;
import com.vaadin.flow.tutorial.binder.data.UsersRepository;
import com.vaadin.flow.tutorial.binder.ui.UsersGrid;
import com.vaadin.flow.tutorial.binder.ui.FormButtonsBar;
import com.vaadin.flow.tutorial.binder.ui.UserForm;

/**
 * The main view contains a button and a click listener.
 */
@Tag("main-view")
@HtmlImport("src/main-view.html")
@Route("")
public class MainView extends PolymerTemplate<TemplateModel> {


    @Id("user-form")
    private UserForm commentForm;

    @Id("users-grid")
    private UsersGrid commentsGrid;

    public MainView() {
        commentsGrid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = commentsGrid.getSelectedItems().stream().findAny();

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
            if ( ! commentForm.getBinder().validate().isOk()){
                return;
            }

            Optional<User> optionalUser = commentForm.getBean();

            if ( optionalUser.isPresent() ){
                //System.out.println(optionalUserComment.get());
                User userComment = optionalUser.get();

                UsersRepository.save( userComment );

                commentsGrid.refresh(userComment);
                commentForm.setBean( userComment);
            }
        });

        formButtonsBar.addCancelListener(cancelEvent -> {
            commentsGrid.deselectAll();
        });

        formButtonsBar.addDeleteListener(deleteEvent -> {
            Optional<User> optionalUser = commentsGrid.getSelectedItems().stream().findAny();

            if ( optionalUser.isPresent() ){
                UsersRepository.delete( optionalUser.get() );
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